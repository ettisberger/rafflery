import React from 'react';
import App, { AppProps } from "./App";
import { hmacAuth } from "../auth/HmacAuth";
import { render } from "@testing-library/react";
import { http } from "../http/http";
import '@testing-library/react/cleanup-after-each'

jest.mock('../http/http');
jest.mock('../pages/home/Home');
jest.mock('../pages/login/Login');

const {
  get
} = http as jest.Mocked<typeof http>;

describe('App', () => {

  const props: AppProps = {
    loggedIn: jest.fn()
  };

  get.mockResolvedValue({ environment: 'test' });

  it('on mount, should call loggedIn when the token is present', async () => {
    hmacAuth.getLoggedInUser = () => 'hanspeter';

    render(<App {...props} />);

    // we need to get rid of async componentDidMount or this will not work
    setTimeout(() => {
      expect(props.loggedIn).toHaveBeenCalledWith('hanspeter');
    });
  });
});
