import { IAction } from '../actions/types';
import { ApplicationState, initialState } from '../root.reducer';

function handleLoggedIn(
  state: ApplicationState,
  action: IAction<{ name: string }>
) {
  return {
    ...state,
    authState: {
      ...state.authState,
      name: action.payload.name,
      isLoggedIn: true,
    },
  };
}

export function authReducer(
  state = initialState,
  action: IAction<any>
): ApplicationState {
  switch (action.type) {
    case 'LOGGED_IN':
      return handleLoggedIn(state, action);
    default:
      return state;
  }
}
