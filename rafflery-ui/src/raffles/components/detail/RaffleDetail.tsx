import React from 'react';
import { Raffle } from '../../../types/Types';
import './RaffleDetail.css';
import { RaffleSlots } from './RaffleSlots';

export interface RaffleDetailProps {
  raffle: Raffle;
  onCancel: () => void;
}

export const RaffleDetail: React.FC<RaffleDetailProps> = ({
  raffle,
  onCancel,
}) => {
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
      </div>
      <RaffleSlots
        maxSlots={raffle.slotSize}
        soldSlots={raffle.purchasedTickets.map(owner => owner.slot)}
      />
      <div className="raffle-detail-actions">
        <button>Purchase</button>
        <button onClick={onCancel}>Cancel</button>
      </div>
    </div>
  );
};
