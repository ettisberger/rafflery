import React, { Component } from 'react';
import './Home.css';
import SearchBar from '../../components/Searchbar';
import Raffles from '../../raffles/components';

interface HomeState {
  raffles: any;
}

class Home extends Component<{}, HomeState> {
  render() {
    return (
      <div className="home">
        <SearchBar />
        <Raffles />
      </div>
    );
  }
}

export default Home;
