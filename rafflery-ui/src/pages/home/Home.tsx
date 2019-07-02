import React from 'react';
import './Home.css';
import RaffleList from '../../components/raffles/RaffleList';
import SearchBar from '../../components/Searchbar';
import axios from 'axios';

const Home: React.FC = () => {

    const getRaffles = async () => {
        const jwt = localStorage.getItem('id_token');
        console.log('using jwt', jwt);
        const raffles = await axios.get('/api/raffles', {
            headers: {
                Authorization: "Bearer " + jwt
            }
        });

        console.log(raffles);
    };

    const raffles = [
        {
            name: 'Rolex GMT Master II',
            totalPrize: 10000,
            slotPrize: 100,
            slotSize: 100,
            purchasedTickets: 45,
            createdBy: 'ninula',
            closesAt: '2019-05-20 10:40:32',
            id: 1,
        },
        {
            name: 'Sofa Vitra',
            totalPrize: 5000,
            slotPrize: 100,
            slotSize: 50,
            purchasedTickets: 12,
            createdBy: 'ninula2',
            closesAt: '2019-05-21 11:40:32',
            id: 2,
        },
        {
            name: 'ASUS Mainboard GTX403',
            totalPrize: 400,
            slotPrize: 20,
            slotSize: 20,
            purchasedTickets: 19,
            createdBy: 'ninula3',
            closesAt: '2019-05-22 12:40:32',
            id: 3,
        },
        {
            name: 'Koalabär',
            totalPrize: 50000,
            slotPrize: 1000,
            slotSize: 50,
            purchasedTickets: 1,
            createdBy: 'ninula4',
            closesAt: '2019-05-23 12:40:32',
            id: 4,
        },
    ];

    return (
        <div className="home">
            <SearchBar/>
            <button onClick={getRaffles}>Geile button zum en request abschicke</button>
            <RaffleList raffles={raffles}/>
        </div>
    );
};

export default Home;
