import { fetchRaffles } from './raffles.api';
import { call, put, takeEvery, all } from 'redux-saga/effects';
import { fetchRafflesSucceeded } from './raffles.actions';
import * as actions from './raffles.actions';

export function* fetchRafflesSaga() {
  const raffles = yield call(fetchRaffles);
  yield put(fetchRafflesSucceeded(raffles));
}

export default function* rafflesSaga() {
  yield all([
    takeEvery(actions.FETCH_RAFFLES_REQUESTED, fetchRafflesSaga)
  ]);
}
