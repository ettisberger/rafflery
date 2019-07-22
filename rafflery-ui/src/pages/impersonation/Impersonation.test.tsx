import { render, fireEvent, RenderResult } from '@testing-library/react';
import Impersonation, { ImpersonationProps } from './Impersonation';
import * as React from 'react';
import { hmacAuth } from '../../auth/HmacAuth';
import '@testing-library/react/cleanup-after-each';

describe('Impersonation Component', () => {
  hmacAuth.setToken = jest.fn();
  hmacAuth.clearToken = jest.fn();

  afterEach(() => jest.resetAllMocks());

  const defaultProps: ImpersonationProps = {
    loggedInUser: '',
    onLoggedIn: jest.fn(),
  };

  describe('when no user is yet selected', () => {
    let component: RenderResult;

    beforeEach(() => {
      component = render(<Impersonation {...defaultProps} />);
    });

    it('should impersonate "Andy" when clicked', () => {
      const andyButton = component.getByText('Andy');
      fireEvent.click(andyButton);

      expect(hmacAuth.setToken).toHaveBeenCalledWith('Andy');
    });

    it('should impersonate "Nick" when clicked', () => {
      const nickButton = component.getByText('Nick');
      fireEvent.click(nickButton);

      expect(hmacAuth.setToken).toHaveBeenCalledWith('Nick');
    });

    it('should clear impersonation when "Guest" is clicked', () => {
      const guestButton = component.getByText('Guest');
      fireEvent.click(guestButton);

      expect(hmacAuth.clearToken).toHaveBeenCalled();
    });

    it('should call loggedIn when a user is selected', () => {
      const nickButton = component.getByText('Nick');
      fireEvent.click(nickButton);

      expect(defaultProps.onLoggedIn).toHaveBeenCalledWith('Nick');
    });
  });

  describe('when a user is selected', () => {
    it('should highlight the selected user', () => {
      const props = { ...defaultProps, loggedInUser: 'Nick' };

      const component = render(<Impersonation {...props} />);
      const nickButton = component.getByText('Nick');
      fireEvent.click(nickButton);

      expect(nickButton.classList).toContain('selected');
    });
  });
});
