import { connect } from 'react-redux';
import * as authActions from '../actions/actions';
import * as actions from './redux/app.actions';
import App from './App';
import { ApplicationState } from '../root.reducer';

const mapStateToProps = (state: ApplicationState) => ({
  environment: state.uiConfig.environment,
  user: state.authState.name,
});

const mapDispatchToProps = {
  fetchEnvironment: actions.fetchUiConfig,
  loggedIn: authActions.loggedIn,
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(App);
