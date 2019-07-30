import React from 'react';
import './RaffleSlot.css';

export interface RaffleSlotProps {
  slot: number;
  sold: boolean;
  selected: boolean;
  onSelect: any;
}

export const RaffleSlot: React.FC<RaffleSlotProps> = ({
  slot,
  sold,
  selected,
  onSelect,
}) => {
  return (
    <div
      className={`slot ${sold ? 'sold' : ''} ${selected ? 'selected' : ''}`}
      unselectable={sold ? 'on' : 'off'}
      data-testid="slot"
      onClick={onSelect}
    >
      <div className="slot__number">{slot}</div>
    </div>
  );
};
