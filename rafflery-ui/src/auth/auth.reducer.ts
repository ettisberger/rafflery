import {AuthState, LoginActionTypes} from '../actions/types';
import AuthService from './AuthService';

const initialState: AuthState = {
    auth: new AuthService(),
    email: 'initial@test.com',
};

export function authReducer(
    state = initialState,
    action: LoginActionTypes,
): AuthState {
    switch (action.type) {
        case 'LOGGED_IN':
            console.log(action);
            console.log(state);
            return {
                ...state,
                email: action.email,
            };
        default:
            return state;
    }
}

// export function authReducer (
//     state = initialState,
//     action: LoginActionTypes
// ) => {
//     switch (action.type) {
//         case LOGIN_REQUEST:
//             return {
//                 ...state,
//                 isFetching: true,
//                 error: null,
//             };
//         case LOGGED_IN:
//             return {
//                 ...state,
//                 isFetching: false,
//                 isAuthenticated: true,
//                 profile: action.payload.profile,
//             };
//         case LOGIN_ERROR:
//             return {
//                 ...state,
//                 isFetching: false,
//                 isAuthenticated: false,
//                 profile: {},
//                 error: action.error,
//             };
//         case LOGGED_OUT:
//             return {
//                 ...state,
//                 isAuthenticated: false,
//                 profile: {},
//             };
//         default:
//             return state;
//     }
// };
