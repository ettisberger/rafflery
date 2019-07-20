import React from 'react';
import RaffleList from './list/RaffleList';
import { Raffle } from '../../types/Types';

export interface RafflesProps {
  raffles: Raffle[];
  fetchRaffles: (...args: any) => any;
}

export class Raffles extends React.Component<RafflesProps> {

  componentDidMount() {
    this.props.fetchRaffles();
  }

  render() {
    return (
      <RaffleList raffles={this.props.raffles}/>
    )
  }
}
