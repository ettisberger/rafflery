import React from 'react';
import './RaffleTile.css';

interface RaffleTileProps {
    raffle: {
        // I guess we need to make an interface for raffle items too...
        name: string,
        item: { name: string, value: number},
        slotSize: number,
        purchasedTickets: [ { owner: string }],
        createdBy: string,
        closesAt: Date,
        id: number,
    };
    color: string;
}

const RaffleTile: React.FC<RaffleTileProps> = ({ raffle, color }) => {
    return (
        <div className="raffleTile">
            <div className="content" style={{backgroundColor:  color }}>
                <div className="title">
                    {raffle.name}
                </div>
                <div>SLOTS {raffle.slotSize}</div>
                <div>SLOT PRIZE {raffle.item.value}</div>
                <div>created by {raffle.createdBy}</div>
            </div>
        </div>
    );
};

export default RaffleTile;
