import { IAction } from '../../actions/types';
import { UiConfig } from '../../types/Types';

export const FETCH_UI_CONFIG_REQUESTED = 'FETCH_UI_CONFIG_REQUESTED';
export const FETCH_UI_CONFIG_SUCCEEDED = 'FETCH_UI_CONFIG_SUCCEEDED';

export const fetchUiConfig = (): IAction<{}> => ({
  type: FETCH_UI_CONFIG_REQUESTED,
  payload: {}
});

export const fetchUiConfigSucceeded = (uiConfig: UiConfig): IAction<UiConfig> => ({
  type: FETCH_UI_CONFIG_SUCCEEDED,
  payload: uiConfig
});
