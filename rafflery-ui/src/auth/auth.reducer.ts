import {AuthState, LoginActionTypes} from '../actions/types';

const initialState: AuthState = {
    email: '',
    isAuthenticating: false,
    isLoggedIn: false,
};

export function authReducer(
    state = initialState,
    action: LoginActionTypes,
): AuthState {
    switch (action.type) {
        case 'LOGGED_IN':
            return {
                ...state,
                email: action.email,
                isLoggedIn: true,
            };
        default:
            return state;
    }
}
