import React from 'react';
import './Home.css';
import SearchBar from '../../components/Searchbar';
import Raffles from '../../raffles/components/list';

const Home: React.FC = () => (
  <div className="home">
    <SearchBar />
    <Raffles />
  </div>
);

export default Home;
