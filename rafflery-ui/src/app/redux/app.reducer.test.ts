import { initialState } from '../../root.reducer';
import { appReducer } from './app.reducer';
import { fetchUiConfigSucceeded } from './app.actions';

describe('app reducer', () => {
  it('should set ui config', () => {
    const state = { ...initialState };
    const action = fetchUiConfigSucceeded({ environment: 'dev' });

    const newState = appReducer(state, action);

    expect(newState.uiConfig.environment).toBe('dev');
  });
});
