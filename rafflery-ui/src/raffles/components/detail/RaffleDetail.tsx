import React from 'react';
import { Raffle } from '../../../types/Types';
import './RaffleDetail.css';
import { RaffleSlots } from './RaffleSlots';

export interface RaffleDetailProps {
  raffle: Raffle;
  onCancel: () => void;
}

export interface RaffleDetailState {
  currentSlots: number;
}

export class RaffleDetail extends React.Component<
  RaffleDetailProps,
  RaffleDetailState
> {
  constructor(props: any) {
    super(props);

    this.state = { currentSlots: 0 };
  }

  public onSlotChange = (currentSlots: number) => {
    this.setState({ currentSlots });
  };

  render() {
    const { raffle } = this.props;
    const price = raffle.item.value / raffle.slotSize;

    return (
      <div className="raffle-detail" data-testid="raffle-detail">
        <h2>Purchase a slot for {raffle.name}</h2>
        <div className="raffle-detail-boxes">
          <div className="raffle-detail-box">
            <div className="raffle-detail-box__title">{raffle.slotSize}</div>
            <div className="raffle-detail-box__description">SLOTS</div>
          </div>
          <div className="raffle-detail-box">
            <div className="raffle-detail-box__title">{raffle.item.value}</div>
            <div className="raffle-detail-box__description">PRICE</div>
          </div>
          <div className="raffle-detail-box">
            <div className="raffle-detail-box__title">
              {raffle.slotSize - raffle.purchasedTickets.length}
            </div>
            <div className="raffle-detail-box__description">SLOTS LEFT</div>
          </div>
          <div className="raffle-detail-box">
            <div className="raffle-detail-box__title">
              {raffle.purchasedTickets.length}
            </div>
            <div className="raffle-detail-box__description">SLOTS SOLD</div>
          </div>
          <div className="raffle-detail-box raffle-detail-box--buyer">
            <div className="raffle-detail-box__title">
              {this.state.currentSlots * price}
            </div>
            <div className="raffle-detail-box__description">CURRENT TOTAL</div>
          </div>
        </div>
        <RaffleSlots
          maxSlots={raffle.slotSize}
          soldSlots={raffle.purchasedTickets.map(owner => owner.slot)}
          onSlotChange={this.onSlotChange}
        />
        <div className="raffle-detail-actions">
          <button>Purchase</button>
          <button onClick={this.props.onCancel}>Cancel</button>
        </div>
      </div>
    );
  }
}
