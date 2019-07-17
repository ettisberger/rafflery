import React, { Component } from 'react';
import './Home.css';
import RaffleList from '../../components/raffles/RaffleList';
import SearchBar from '../../components/Searchbar';
import { http } from "../../http/http";

interface HomeState {
  raffles: any;
}

class Home extends Component<{}, HomeState> {
  constructor(props: any) {
    super(props);

    this.state = { raffles: [] };
  }

  async componentDidMount() {
    const raffles = await http.get('/api/raffles');
    this.setState({ raffles });
  }

  render() {
    return (
      <div className="home">
        <SearchBar/>
        <RaffleList raffles={this.state.raffles}/>
      </div>
    );
  }

}

export default Home;
