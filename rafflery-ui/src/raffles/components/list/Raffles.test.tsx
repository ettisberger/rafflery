import React from 'react';
import { Raffles, RafflesProps } from './Raffles';
import { someRaffles } from '../../raffles.testData';
import { cleanup, fireEvent, render } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';

describe('Raffles Component', () => {
  const defaultProps: RafflesProps = {
    raffles: someRaffles,
    history: {
      push: jest.fn(),
    },
    fetchRaffles: jest.fn(),
  };

  afterEach(cleanup);

  it('should only display list of raffles when no raffle is selected', () => {
    const component = render(<Raffles {...defaultProps} />);

    const raffleTiles = component.getAllByTestId('raffle-tile');
    expect(raffleTiles.length).toBe(someRaffles.length);
    expect(component.queryByTestId('raffle-detail')).toBeFalsy();
    raffleTiles.forEach(tile => expect(tile).not.toHaveClass('hidden'));
  });

  it('should go to /raffles/:id when a raffle is selected', () => {
    const component = render(<Raffles {...defaultProps} />);

    const raffles = component.getAllByTestId('raffle-tile');
    const raffleTileToSelect = raffles[0];

    fireEvent.click(raffleTileToSelect);

    expect(defaultProps.history.push).toHaveBeenCalledWith(
      `/raffles/${someRaffles[0].id}`
    );
  });
});
