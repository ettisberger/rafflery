import React from 'react';
import { Raffle } from '../../../types/Types';
import './RaffleDetail.css';
import { RaffleSlots } from './RaffleSlots';

export interface RaffleDetailProps {
  raffle: Raffle;
  fetchRaffles: () => void;
  purchaseSlots: (raffle: Raffle, slots: number[]) => void;
}

export interface RaffleDetailState {
  currentSlots: number[];
}

export class RaffleDetail extends React.Component<RaffleDetailProps, RaffleDetailState> {
  constructor(props: any) {
    super(props);
    console.log(this.props);
    this.state = { currentSlots: [] };
  }

  componentDidMount() {
    this.props.fetchRaffles();
  }

  onSlotChange = (slots: number[]) => {
    this.setState({ currentSlots: slots });
  };

  purchaseSlots = () => {
    this.props.purchaseSlots(this.props.raffle, this.state.currentSlots);
  };

  render() {
    const { raffle } = this.props;

    if (!raffle) {
      return <div />
    }

    const price = raffle.item.value / raffle.slotSize;

    return (
      <div className="raffle-detail" data-testid="raffle-detail">
        <h2>Purchase a slot for {raffle.name}</h2>
        <div className="raffle-detail-boxes">
          <div className="raffle-detail-box" data-testid="raffle-detail-box">
            <div className="raffle-detail-box__title">{raffle.slotSize}</div>
            <div className="raffle-detail-box__description">SLOTS</div>
          </div>
          <div className="raffle-detail-box" data-testid="raffle-detail-box">
            <div className="raffle-detail-box__title">{price}</div>
            <div className="raffle-detail-box__description">PRICE CHF / SLOT</div>
          </div>
          <div className="raffle-detail-box" data-testid="raffle-detail-box">
            <div className="raffle-detail-box__title">
              {raffle.slotSize - raffle.purchasedTickets.length}
            </div>
            <div className="raffle-detail-box__description">SLOTS LEFT</div>
          </div>
          <div className="raffle-detail-box" data-testid="raffle-detail-box">
            <div className="raffle-detail-box__title">
              {raffle.purchasedTickets.length}
            </div>
            <div className="raffle-detail-box__description">SLOTS SOLD</div>
          </div>
          <div
            className="raffle-detail-box raffle-detail-box--buyer"
            data-testid="raffle-detail-box"
          >
            <div className="raffle-detail-box__title">
              {this.state.currentSlots.length * price}
            </div>
            <div className="raffle-detail-box__description">CURRENT TOTAL CHF</div>
          </div>
        </div>
        <RaffleSlots
          maxSlots={raffle.slotSize}
          soldSlots={raffle.purchasedTickets.map(owner => owner.slot)}
          onSlotChange={this.onSlotChange}
        />
        <div className="raffle-detail-actions">
          <button
            data-testid="purchase-button"
            disabled={this.state.currentSlots.length === 0}
            onClick={this.purchaseSlots}
          >
            Purchase
          </button>
        </div>
      </div>
    );
  }
}
