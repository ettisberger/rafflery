import { authReducer } from './auth/auth.reducer';
import { AuthState } from './actions/types';
import { rafflesReducer } from './raffles/redux/raffles.reducer';
import reduceReducers from 'reduce-reducers';
import { Raffle, UiConfig } from './types/Types';
import { appReducer } from './app/redux/app.reducer';

export interface ApplicationState {
    authState: AuthState;
    raffles: Raffle[]
    uiConfig: UiConfig
}

export const initialState: ApplicationState = {
    authState: {
        name: '',
        isAuthenticating: false,
        isLoggedIn: false,
    },
    raffles: [],
    uiConfig: {
        environment: ''
    },
};

const rootReducer = reduceReducers(
  // @ts-ignore
  rafflesReducer,
  authReducer,
  appReducer,
);

export default rootReducer;
