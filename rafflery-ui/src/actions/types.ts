// action types

import AuthService from '../auth/AuthService';

export const LOGGED_IN = 'LOGGED_IN';
export const LOGGED_OUT = 'LOGGED_OUT';
export const LOGIN_ERROR = 'LOGIN_ERROR';
export const LOGIN_REQUEST = 'LOGIN_REQUEST';

export interface AuthState {
    auth: AuthService;
    email: string;
}

interface LoggedInAction {
    type: typeof LOGGED_IN;
    email: string;
}

export type LoginActionTypes = LoggedInAction;
