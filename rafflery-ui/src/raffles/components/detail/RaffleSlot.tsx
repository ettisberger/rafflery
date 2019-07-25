import React from 'react';
import './RaffleSlot.css';

export interface RaffleSlotProps {
  slot: number;
  sold: boolean;
}

export const RaffleSlot: React.FC<RaffleSlotProps> = ({ slot, sold }) => {
  return (
    <div
      className={`slot ${sold ? 'sold' : ''}`}
      unselectable={sold ? 'on' : 'off'}
      data-testid="slot"
    >
      <div className="slot__number">{slot}</div>
    </div>
  );
};
