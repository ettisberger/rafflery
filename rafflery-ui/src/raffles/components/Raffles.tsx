import React from 'react';
import { Raffle } from '../types/Raffle';
import RaffleList from './list/RaffleList';

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
      <RaffleList raffles={this.props.raffles || []}/>
    )
  }
}
