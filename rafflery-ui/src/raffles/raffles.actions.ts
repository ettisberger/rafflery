import { IAction } from "../actions/types";
import { Raffle } from "./types/Raffle";

export const FETCH_RAFFLES_REQUESTED = 'FETCH_RAFFLES_REQUESTED';
export const FETCH_RAFFLES_SUCCEEDED = 'FETCH_RAFFLES_SUCCEEDED';

export const fetchRaffles = (): IAction<{}> => ({
  type: FETCH_RAFFLES_REQUESTED,
  payload: {}
});

export const fetchRafflesSucceeded = (raffles: Raffle[]): IAction<Raffle[]> => ({
  type: FETCH_RAFFLES_SUCCEEDED,
  payload: raffles
});
