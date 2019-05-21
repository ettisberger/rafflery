import React from 'react';
import './RaffleTile.css';

interface RaffleTileProps {
    raffle: {};
}

const RaffleTile: React.FC<RaffleTileProps> = ({ raffle }) => {
    return (
        <div className="raffleTile">
            <div className="content">
                test
            </div>
        </div>
    );
};

export default RaffleTile;
