import React from 'react';
import './RaffleList.css';
import RaffleTile from './RaffleTile';

interface RaffleListProps {
    raffles: any[];
}

const colors = ['#244F75', '#60BFBF', '#8C4B7E', '#F8BB44', '#F24B4B'];

const RaffleList: React.FC<RaffleListProps> = ({raffles}) => {
    return (
        <div className="raffleList">
            {raffles.map(raffle =>
                <RaffleTile
                    raffle={raffle}
                    key={raffle.id}
                    color={colors[Math.floor((Math.random() * 5) )]}
                />)
            }
        </div>
    );
};

export default RaffleList;
