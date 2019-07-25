import React from 'react';
import { RaffleSlot } from './RaffleSlot';
import './RaffleSlots.css';

export interface RaffleSlotsProps {
  maxSlots: number;
  soldSlots: number[];
}

export const RaffleSlots: React.FC<RaffleSlotsProps> = ({
  maxSlots,
  soldSlots,
}) => {
  const slots = [];

  for (let i = 0; i < maxSlots; i++) {
    slots.push(
      <RaffleSlot sold={soldSlots.includes(i)} slot={i + 1} key={i + 1} />
    );
  }

  return (
    <div className="raffle-slots" data-testid="raffle-slots">
      {slots}
    </div>
  );
};
