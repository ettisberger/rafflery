import { expectSaga } from 'redux-saga-test-plan';
import { fetchUiConfigSaga } from './app.saga';
import { fetchUiConfig } from '../app.api';
import { UiConfig } from '../../types/Types';
import { fetchUiConfigSucceeded } from './app.actions';
import { call } from 'redux-saga/effects';

describe('App saga', () => {

  const mockUiConfig: UiConfig = {
    environment: 'dev'
  };

  it('should fetch ui config', () => {
    return expectSaga(fetchUiConfigSaga)
      .provide([
        [call(fetchUiConfig), mockUiConfig]
      ])
      .put(fetchUiConfigSucceeded(mockUiConfig))
      .run();
  });

});
