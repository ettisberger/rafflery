import React from 'react';
import './RaffleTile.css';
import { Raffle } from '../../../types/Types';

export interface RaffleTileProps {
    raffle: Raffle;
    color: string;
    hidden: boolean;
    onSelect: () => void;
}

const RaffleTile: React.FC<RaffleTileProps> = ({ raffle, color, hidden, onSelect }) => {
    const numberOfSlots = raffle.item.value / raffle.slotSize;

    return (
        <div
          className={`raffleTile ${hidden ? 'hidden' : ''}`}
          data-testid="raffle-tile"
        >
            <div
              data-testid="raffle-item"
              className="content"
              style={{backgroundColor: color}}
              onClick={onSelect}
            >
                <div className="title">
                    {raffle.name}
                </div>
                <div className="raffle-info">SLOT SIZE {raffle.slotSize.toLocaleString()}</div>
                <div className="raffle-info">VALUE CHF {raffle.item.value.toLocaleString()}</div>
                <div className="raffle-info">{raffle.purchasedTickets.length} / {numberOfSlots} sold</div>
                <div className="raffle-info">created by {raffle.createdBy}</div>
            </div>
        </div>
    );
};

export default RaffleTile;
