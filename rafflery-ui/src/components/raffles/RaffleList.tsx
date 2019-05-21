import React from 'react';
import './RaffleList.css';
import RaffleTile from './RaffleTile';

interface RaffleListProps {
    raffles: any[];
}

const RaffleList: React.FC<RaffleListProps> = ({ raffles }) => {
    return (
        <div className="raffleList">
            {raffles.map( raffle => <RaffleTile raffle={raffle} key={raffle.id}/>)}
        </div>
    );
};

export default RaffleList;
