import React from 'react';
import App, { AppProps } from './App';
import { hmacAuth } from '../auth/HmacAuth';
import { render } from '@testing-library/react';
import { http } from '../http/http';
import '@testing-library/react/cleanup-after-each';

jest.mock('../http/http');
jest.mock('../pages/home/Home', () => { 'div' });
jest.mock('../pages/login/Login');

const { get } = http as jest.Mocked<typeof http>;

describe('App', () => {
  const props: AppProps = {
    environment: '',
    loggedIn: jest.fn(),
    fetchEnvironment: jest.fn(),
    user: 'andy'
  };

  get.mockResolvedValue({ environment: 'test' });

  it('on mount, should call loggedIn when the token is present', async () => {
    hmacAuth.getLoggedInUser = () => 'hanspeter';

    render(<App {...props} />);

    expect(props.loggedIn).toHaveBeenCalledWith('hanspeter');
  });

  it('on mount, should call fetchEnvironment', async () => {
    render(<App {...props} />);

    expect(props.fetchEnvironment).toHaveBeenCalled();
  });
});
