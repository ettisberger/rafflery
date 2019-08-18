import { fetchRaffles, purchaseSlots } from '../raffles.api';
import { all, call, put, takeEvery } from 'redux-saga/effects';
import * as actions from './raffles.actions';
import { fetchRafflesSucceeded } from './raffles.actions';
import { IAction } from '../../actions/types';

export function* fetchRafflesSaga() {
  const raffles = yield call(fetchRaffles);
  yield put(fetchRafflesSucceeded(raffles));
}

export function* purchaseSlotsSaga(action: IAction<{raffleId: string, slots: number[]}>) {
  const {raffleId, slots} = action.payload;
  yield call(purchaseSlots, raffleId, slots);
  yield put(actions.fetchRaffles());
}

export default function* rafflesSaga() {
  yield all([
    takeEvery(actions.FETCH_RAFFLES_REQUESTED, fetchRafflesSaga),
    takeEvery(actions.PURCHASE_SLOTS_REQUESTED, purchaseSlotsSaga)
  ]);
}
