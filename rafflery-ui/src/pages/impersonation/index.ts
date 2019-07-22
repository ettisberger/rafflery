import { ApplicationState } from '../../root.reducer';
import Impersonation from './Impersonation';
import { connect } from 'react-redux';
import * as actions from '../../actions/actions';

const mapStateToProps = (state: ApplicationState) => ({
  loggedInUser: state.authState.name,
});

const mapDispatchToProps = {
  onLoggedIn: actions.loggedIn,
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Impersonation);
