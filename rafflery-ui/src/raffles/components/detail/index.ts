import { ApplicationState } from '../../../root.reducer';
import { connect } from 'react-redux';
import { RaffleDetail } from './RaffleDetail';
import * as actions from '../../redux/raffles.actions'

const mapStateToProps = (
  state: ApplicationState,
  ownProps: any
) => ({
  raffle: state.raffles.find(raffle => raffle.id === ownProps.match.params.id)
});

const mapDispatchToProps = {
  fetchRaffles: actions.fetchRaffles
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(RaffleDetail)
