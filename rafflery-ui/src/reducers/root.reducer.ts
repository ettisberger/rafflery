import {combineReducers} from 'redux';
import {authReducer} from '../auth/auth.reducer';
import {AuthState} from '../actions/types';

export interface ApplicationState {
    authState: AuthState;
}

const rootReducer = combineReducers<ApplicationState>({
    authState: authReducer,
});

export default rootReducer;
