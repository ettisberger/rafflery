import { IAction } from "../../actions/types";
import { Raffle } from '../../types/Types';

export const FETCH_RAFFLES_REQUESTED = 'FETCH_RAFFLES_REQUESTED';
export const FETCH_RAFFLES_SUCCEEDED = 'FETCH_RAFFLES_SUCCEEDED';
export const SELECT_RAFFLE = 'SELECT_RAFFLE';
export const UNSELECT_RAFFLE = 'UNSELECT_RAFFLE';

export const fetchRaffles = (): IAction<{}> => ({
  type: FETCH_RAFFLES_REQUESTED,
  payload: {}
});

export const fetchRafflesSucceeded = (raffles: Raffle[]): IAction<Raffle[]> => ({
  type: FETCH_RAFFLES_SUCCEEDED,
  payload: raffles
});

export const selectRaffle = (raffleId: string): IAction<string> => ({
  type: SELECT_RAFFLE,
  payload: raffleId
});

export const unselectRaffle = (): IAction<{}> => ({
  type: UNSELECT_RAFFLE,
  payload: {}
});
