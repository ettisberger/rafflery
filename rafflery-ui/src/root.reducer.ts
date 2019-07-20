import { authReducer } from './auth/auth.reducer';
import { AuthState } from './actions/types';
import { Raffle } from './raffles/types/Raffle';
import { rafflesReducer } from './raffles/raffles.reducer';
import reduceReducers from 'reduce-reducers';

export interface ApplicationState {
    authState: AuthState;
    raffles: Raffle[]
}

export const initialState: ApplicationState = {
    authState: {
        name: '',
        isAuthenticating: false,
        isLoggedIn: false,
    },
    raffles: []
};

const rootReducer = reduceReducers(
  // @ts-ignore
  rafflesReducer,
  authReducer,
);

export default rootReducer;
