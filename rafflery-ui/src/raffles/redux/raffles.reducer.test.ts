import { rafflesReducer } from './raffles.reducer';
import {
  fetchRafflesSucceeded,
} from './raffles.actions';
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

});
