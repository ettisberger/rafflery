import { ApplicationState } from '../../../root.reducer';
import * as actions from '../../redux/raffles.actions';
import { connect } from 'react-redux';
import { Raffles } from './Raffles';
import { withRouter } from 'react-router';

const mapStateToProps = (
  state: ApplicationState,
  ownProps: any
) => ({
  raffles: state.raffles,
  selectedRaffle: state.selectedRaffle && state.selectedRaffle,
  history: ownProps.history
});

const mapDispatchToProps = {
  fetchRaffles: actions.fetchRaffles,
  selectRaffle: actions.selectRaffle,
  unselectRaffle: actions.unselectRaffle,
};

export default withRouter(connect(
  mapStateToProps,
  mapDispatchToProps
)(Raffles));
