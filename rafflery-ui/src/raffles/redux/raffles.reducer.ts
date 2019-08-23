import { IAction } from '../../actions/types';
import { ApplicationState, initialState } from '../../root.reducer';
import * as actions from './raffles.actions';
import { Raffle } from '../../types/Types';

function onFetchRafflesSucceeded(
  state: ApplicationState,
  action: IAction<Raffle[]>
): ApplicationState {
  return {
    ...state,
    raffles: action.payload,
  };
}

export function rafflesReducer(
  state = initialState,
  action: IAction<any>
): ApplicationState {
  switch (action.type) {
    case actions.FETCH_RAFFLES_SUCCEEDED:
      return onFetchRafflesSucceeded(state, action);
    default:
      return state;
  }
}
