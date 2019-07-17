// action types

export const LOGGED_IN = 'LOGGED_IN';
export const LOGGED_OUT = 'LOGGED_OUT';
export const LOGIN_ERROR = 'LOGIN_ERROR';
export const LOGIN_REQUEST = 'LOGIN_REQUEST';

export interface AuthState {
    isAuthenticating: boolean;
    isLoggedIn: boolean;
    name: string;
}

interface LoggedInAction {
    type: typeof LOGGED_IN;
    name: string;
}

export type LoginActionTypes = LoggedInAction;
