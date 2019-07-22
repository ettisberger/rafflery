import { expectSaga } from 'redux-saga-test-plan';
import { fetchRafflesSaga } from './raffles.saga';
import { call } from 'redux-saga-test-plan/matchers';
import { fetchRaffles } from '../raffles.api';
import { fetchRafflesSucceeded } from './raffles.actions';
import { someRaffles } from '../raffles.testData';

describe('raffles saga', () => {
  it('should fetch raffles', () => {
    return expectSaga(fetchRafflesSaga)
      .provide([[call(fetchRaffles), someRaffles]])
      .put(fetchRafflesSucceeded(someRaffles))
      .run();
  });
});
