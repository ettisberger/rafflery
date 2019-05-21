import React from 'react';
import './RaffleTile.css';

interface RaffleTileProps {
    raffle: {
        // I guess we need to make an interface for raffle items too...
        name: string,
        totalPrize: number,
        slotPrize: number,
        slotSize: number,
        purchasedTickets: number,
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
                <div>SLOTS {raffle.purchasedTickets} / {raffle.slotSize}</div>
                <div>TOTAL {raffle.totalPrize}</div>
                <div>SLOT PRIZE {raffle.slotPrize}</div>
                <div>closes at {raffle.closesAt}</div>
            </div>
        </div>
    );
};

export default RaffleTile;
