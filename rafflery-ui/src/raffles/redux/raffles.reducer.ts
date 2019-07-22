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

function onSelectRaffle(
  state: ApplicationState,
  action: IAction<string>
): ApplicationState {
  return {
    ...state,
    selectedRaffle: state.raffles.find(raffle => raffle.id === action.payload),
  };
}

function onUnSelectRaffle(state: ApplicationState): ApplicationState {
  return {
    ...state,
    selectedRaffle: undefined,
  };
}

export function rafflesReducer(
  state = initialState,
  action: IAction<any>
): ApplicationState {
  switch (action.type) {
    case actions.FETCH_RAFFLES_SUCCEEDED:
      return onFetchRafflesSucceeded(state, action);
    case actions.SELECT_RAFFLE:
      return onSelectRaffle(state, action);
    case actions.UNSELECT_RAFFLE:
      return onUnSelectRaffle(state);
    default:
      return state;
  }
}
