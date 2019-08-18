import React from 'react';
import RaffleTile from './tile/RaffleTile';
import './Raffles.css';
import { Raffle } from '../../../types/Types';

export interface RafflesProps {
  raffles: Raffle[];
  selectedRaffle?: Raffle;
  fetchRaffles: (...args: any) => any;
  selectRaffle: (...args: any) => any;
  unselectRaffle: (...args: any) => any;
  history: any;
}

const colors = ['#244F75', '#60BFBF', '#8C4B7E', '#F8BB44', '#F24B4B'];

export class Raffles extends React.Component<RafflesProps> {
  componentDidMount() {
    this.props.fetchRaffles();
  }

  goToDetail(raffle: Raffle) {
    this.props.history.push(`/raffles/${raffle.id}`);
  }

  render() {
    const selectedRaffle = this.props.selectedRaffle;
    const isHidden = (raffle: Raffle) =>
      !!(selectedRaffle && selectedRaffle.id !== raffle.id);

    return (
      <div>
        <div className="raffle-list">
          {this.props.raffles.map((raffle, i) => (
            <RaffleTile
              hidden={isHidden(raffle)}
              onSelect={() => this.goToDetail(raffle)}
              raffle={raffle}
              key={raffle.id}
              color={colors[i % colors.length]}
            />
          ))}
        </div>
      </div>
    );
  }
}
