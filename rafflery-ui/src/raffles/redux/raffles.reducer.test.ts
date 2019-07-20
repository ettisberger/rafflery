import { rafflesReducer } from './raffles.reducer';
import { fetchRafflesSucceeded, selectRaffle, unselectRaffle } from './raffles.actions';
import { initialState } from '../../root.reducer';
import { aRaffle, someRaffles } from '../raffles.testData';

describe('Raffles reducer', () => {

  it('should initially return empty raffles', () => {
    const newState = rafflesReducer(undefined, fetchRafflesSucceeded([]));
    expect(newState.raffles).toEqual([]);
  });

  it('should replace raffles on the state', () => {
    const state = { ...initialState };
    const action = fetchRafflesSucceeded(someRaffles);

    const newState = rafflesReducer(state, action);

    expect(newState.raffles).toEqual(someRaffles);
  });

  it('should select a raffle', () => {
    const state = { ...initialState, raffles: someRaffles };
    const raffleToSelect = someRaffles[0];
    const action = selectRaffle(raffleToSelect.id);

    const newState = rafflesReducer(state, action);

    expect(newState.selectedRaffle).toEqual(raffleToSelect);
  });

  it('should unselect a raffle', () => {
    const state = { ...initialState, selectedRaffle: aRaffle };
    const action = unselectRaffle();

    const newState = rafflesReducer(state, action);

    expect(newState.selectedRaffle).toBeUndefined();
  });

});
