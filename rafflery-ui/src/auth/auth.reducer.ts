import { AuthState, LoginActionTypes } from '../actions/types';

const initialState: AuthState = {
  name: '',
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
        name: action.name,
        isLoggedIn: true,
      };
    default:
      return state;
  }
}
