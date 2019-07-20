import { ApplicationState, initialState } from '../../root.reducer';
import { IAction } from '../../actions/types';
import * as actions from './app.actions';
import { UiConfig } from '../../types/Types';

function handleFetchUiConfigSucceeded(state: ApplicationState, action: IAction<UiConfig>) {
  return {
    ...state,
    uiConfig: action.payload
  };
}

export function appReducer(state = initialState, action: IAction<any>): ApplicationState {
  switch (action.type) {
    case actions.FETCH_UI_CONFIG_SUCCEEDED:
      return handleFetchUiConfigSucceeded(state, action);
  }
  return state;
}
