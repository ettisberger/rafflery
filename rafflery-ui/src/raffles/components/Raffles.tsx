import React from 'react';
import { Raffle } from '../../types/Types';
import RaffleTile from './tile/RaffleTile';
import './Raffles.css';
import { SlotPurchase } from './slotPurchase/SlotPurchase';

export interface RafflesProps {
  raffles: Raffle[];
  selectedRaffle?: Raffle;
  fetchRaffles: (...args: any) => any;
  selectRaffle: (...args: any) => any;
  unselectRaffle: (...args: any) => any;
}

const colors = ['#244F75', '#60BFBF', '#8C4B7E', '#F8BB44', '#F24B4B'];

export class Raffles extends React.Component<RafflesProps> {

  componentDidMount() {
    this.props.fetchRaffles();
  }

  render() {
    const selectedRaffle = this.props.selectedRaffle;
    const isHidden = (raffle: Raffle) => !!(selectedRaffle && selectedRaffle.id !== raffle.id);

    return (
      <div>
        <div className="raffleList">
          {this.props.raffles.map((raffle, i) =>
            <RaffleTile
              hidden={isHidden(raffle)}
              onSelect={() => this.props.selectRaffle(raffle.id)}
              raffle={raffle}
              key={raffle.id}
              color={colors[i % colors.length]}
            />
          )}
        </div>
        {
          selectedRaffle &&
          <SlotPurchase
              onCancel={() => this.props.unselectRaffle()}
              raffle={selectedRaffle}
          />
        }
      </div>
    )
  }
}
