import { fetchUiConfig } from '../app.api';
import * as actions from './app.actions';
import { fetchUiConfigSucceeded } from './app.actions';
import { all, call, put, takeEvery } from 'redux-saga/effects';

export function* fetchUiConfigSaga() {
  const uiConfig = yield call(fetchUiConfig);
  yield put(fetchUiConfigSucceeded(uiConfig));
}

export default function* appSaga() {
  yield all([takeEvery(actions.FETCH_UI_CONFIG_REQUESTED, fetchUiConfigSaga)]);
}
