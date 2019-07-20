import rafflesSaga from './raffles/redux/raffles.saga';
import { all } from 'redux-saga/effects';
import appSaga from './app/redux/app.saga';

export default function* rootSaga() {
  yield all([
    rafflesSaga(),
    appSaga()
  ]);
}
