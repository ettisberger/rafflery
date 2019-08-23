import { expectSaga } from 'redux-saga-test-plan';
import { fetchRafflesSaga, purchaseSlotsSaga } from './raffles.saga';
import { call } from 'redux-saga-test-plan/matchers';
import { fetchRaffles, purchaseSlots } from '../raffles.api';
import { fetchRafflesSucceeded } from './raffles.actions';
import { aRaffle, someRaffles } from '../raffles.testData';
import * as actions from './raffles.actions';

describe('raffles saga', () => {
  it('should fetch raffles', () => {
    return expectSaga(fetchRafflesSaga)
      .provide([
        [call(fetchRaffles), someRaffles]
      ])
      .put(fetchRafflesSucceeded(someRaffles))
      .run();
  });

  it('should purchase slots', () => {
    const action = actions.purchaseSlots(aRaffle, [1]);

    return expectSaga(purchaseSlotsSaga, action)
      .provide([
        [call(purchaseSlots, aRaffle.id, [1]), {}],
      ])
      .call(purchaseSlots, aRaffle.id, [1])
      .put(actions.fetchRaffles())
      .run()
  });
});
