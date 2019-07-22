import React from 'react';
import { Raffle } from '../../../types/Types';
import './RaffleDetail.css';
import { RaffleSlot } from './RaffleSlot';

export interface RaffleDetailProps {
  raffle: Raffle;
  onCancel: () => void;
}

export const RaffleDetail: React.FC<RaffleDetailProps> = ({
  raffle,
  onCancel,
}) => {
  const slots = [];

  for (let i = 0; i < raffle.slotSize; i++) {
    slots.push(<RaffleSlot raffleSlot={i + 1} key={i + 1} />);
  }

  return (
    <div className="raffle-detail" data-testid="raffle-detail">
      <h2>Purchase a slot for {raffle.name}</h2>
      <div className="raffle-detail__slots" data-testid="raffle-detail-slots">
        {slots}
      </div>
      <div className="raffle-detail__actions">
        <button>Purchase</button>
        <button onClick={onCancel}>Cancel</button>
      </div>
    </div>
  );
};
