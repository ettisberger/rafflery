import { IAction } from '../../actions/types';
import { Raffle } from '../../types/Types';

export const FETCH_RAFFLES_REQUESTED = 'FETCH_RAFFLES_REQUESTED';
export const FETCH_RAFFLES_SUCCEEDED = 'FETCH_RAFFLES_SUCCEEDED';

export const PURCHASE_SLOTS_REQUESTED = 'PURCHASE_SLOTS_REQUESTED';

export const fetchRaffles = (): IAction<{}> => ({
  type: FETCH_RAFFLES_REQUESTED,
  payload: {},
});

export const fetchRafflesSucceeded = (
  raffles: Raffle[]
): IAction<Raffle[]> => ({
  type: FETCH_RAFFLES_SUCCEEDED,
  payload: raffles,
});

export const purchaseSlots = (
  raffle: Raffle,
  slots: number[]
): IAction<{raffleId: string, slots: number[]}> => ({
  type: PURCHASE_SLOTS_REQUESTED,
  payload: {
    raffleId: raffle.id,
    slots
  }
});
