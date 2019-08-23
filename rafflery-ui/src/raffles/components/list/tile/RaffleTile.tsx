import React from 'react';
import './RaffleTile.css';
import { Raffle } from '../../../../types/Types';

export interface RaffleTileProps {
  raffle: Raffle;
  color: string;
  onSelect: () => void;
}

const RaffleTile: React.FC<RaffleTileProps> = ({ raffle, color, onSelect, }) => {
  const numberOfSlots = raffle.item.value / raffle.slotSize;

  const getImageSrc = (raffle: Raffle): string => {
    switch (raffle.name) {
      case 'Rolex GMT Master II':
        return './images/rolex.jpeg';
      case 'Sofa Vitra':
        return './images/sofavitra.jpeg';
      case 'ASUS Mainboard GTX403':
        return './images/mainboard.jpeg';
      default:
        return './images/panda.jpeg';
    }
  };

  return (
    <div
      className="raffle-tile"
      data-testid="raffle-tile"
      onClick={onSelect}
    >
      <div className="product-image">
        <img alt="product" src={getImageSrc(raffle)}/>
      </div>
      <div className="title">{raffle.name}</div>
      <div className="raffle-details">
        <div>Total value</div>
        <div
          data-testid="raffle-detail-total-value"
        >
          CHF {raffle.item.value.toLocaleString()}.00
        </div>

        <div>Slot size</div>
        <div
          data-testid="raffle-detail-slot-size"
        >
          CHF {raffle.slotSize.toLocaleString()}.00
        </div>

        <div>Sold</div>
        <div
          data-testid="raffle-detail-sold-slots"
        >
          {raffle.purchasedTickets.length} / {numberOfSlots}
        </div>
      </div>
    </div>
  );
};

export default RaffleTile;
