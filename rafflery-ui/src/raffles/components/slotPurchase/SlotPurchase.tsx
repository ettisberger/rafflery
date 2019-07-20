import React from 'react';
import { Raffle } from '../../../types/Types';
import './SlotPurchase.css';

interface SlotPurchaseProps {
  raffle: Raffle;
  onCancel: () => void;
}

export const SlotPurchase: React.FC<SlotPurchaseProps> = ({ raffle, onCancel }) => (
  <div
    className="slot-purchase"
    data-testid="slot-purchase"
  >
    <h2>Purchase a slot for {raffle.name}</h2>
    <button className="purchase-button">Purchase</button>
    <button
      onClick={onCancel}
      className="cancel-button"
    >Cancel</button>
  </div>
);
