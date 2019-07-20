import { ApplicationState } from '../../root.reducer';
import * as actions from '../redux/raffles.actions';
import { connect } from 'react-redux';
import { Raffles } from './Raffles';

const mapStateToProps = (state: ApplicationState) => ({
  raffles: state.raffles,
  selectedRaffle: state.selectedRaffle && state.selectedRaffle
});

const mapDispatchToProps = {
  fetchRaffles: actions.fetchRaffles,
  selectRaffle: actions.selectRaffle,
  unselectRaffle: actions.unselectRaffle
};

export default connect(mapStateToProps, mapDispatchToProps)(Raffles)
