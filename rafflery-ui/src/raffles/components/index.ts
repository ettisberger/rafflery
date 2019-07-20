import { ApplicationState } from '../../root.reducer';
import * as actions from '../raffles.actions';
import { connect } from 'react-redux';
import { Raffles } from './Raffles';

const mapStateToProps = (state: ApplicationState) => {
  console.log(state);
  return ({
    raffles: state.raffles
  });
};

const mapDispatchToProps = {
  fetchRaffles: actions.fetchRaffles
};

export default connect(mapStateToProps, mapDispatchToProps)(Raffles)
