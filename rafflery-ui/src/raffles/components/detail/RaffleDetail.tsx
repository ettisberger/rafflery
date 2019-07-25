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
      <RaffleSlots
        maxSlots={raffle.slotSize}
        soldSlots={raffle.purchasedTickets.map(owner => owner.slot)}
      />
      <div className="raffle-detail__actions">
        <button>Purchase</button>
        <button onClick={onCancel}>Cancel</button>
      </div>
    </div>
  );
};
