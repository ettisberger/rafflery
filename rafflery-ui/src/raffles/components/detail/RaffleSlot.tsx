import React from 'react';
import './RaffleSlot.css';

export interface RaffleSlotProps {
  raffleSlot: number;
}

export const RaffleSlot: React.FC<RaffleSlotProps> = ({ raffleSlot }) => (
  <div className="slot" data-testid="slot">
    <div className="slot__number">{raffleSlot}</div>
  </div>
);
