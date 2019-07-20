import React from 'react';
import { Raffles, RafflesProps } from './Raffles';
import { someRaffles } from '../raffles.testData';
import { render, fireEvent, cleanup } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect'

describe('Raffles Component', () => {

  const defaultProps: RafflesProps = {
    raffles: someRaffles,
    selectedRaffle: undefined,
    fetchRaffles: jest.fn(),
    selectRaffle: jest.fn(),
    unselectRaffle: jest.fn()
  };

  afterEach(cleanup);

  it('should only display list of raffles when no raffle is selected', () => {
    const component = render(<Raffles {...defaultProps}/>);

    const raffleTiles = component.getAllByTestId('raffle-item');
    expect(raffleTiles.length).toBe(someRaffles.length);
    expect(component.queryByTestId('slot-purchase')).toBeFalsy();
    raffleTiles.forEach(tile => expect(tile).not.toHaveClass('hidden'));
  });

  it('should call selectRaffle when a raffle is clicked', () => {
    const component = render(<Raffles {...defaultProps}/>);

    const raffles = component.getAllByTestId('raffle-item');
    const raffleTileToSelect = raffles[0];

    fireEvent.click(raffleTileToSelect);

    expect(defaultProps.selectRaffle).toHaveBeenCalledWith(someRaffles[0].id);
  });

  it('should display slot purchase option of raffle when selected', () => {
    const props = { ...defaultProps, selectedRaffle: someRaffles[0] };
    const component = render(<Raffles {...props}/>);

    const slotPurchase = component.queryByTestId('slot-purchase');
    expect(slotPurchase).toBeTruthy();
    expect(component.queryByText(`Purchase a slot for ${someRaffles[0].name}`));
  });

  it('should hide other raffles when one is selected', () => {
    const props = { ...defaultProps, selectedRaffle: someRaffles[0] };
    const component = render(<Raffles {...props}/>);

    const raffleTiles = component.getAllByTestId('raffle-tile');
    expect(raffleTiles[0]).not.toHaveClass('hidden');
    raffleTiles.slice(1).forEach(tile => expect(tile).toHaveClass('hidden'));
  });

  it('should call unselectRaffle when purchase is cancelled', () => {
    const props = { ...defaultProps, selectedRaffle: someRaffles[0] };
    const component = render(<Raffles {...props}/>);

    const cancelButton = component.getByText('Cancel');

    fireEvent.click(cancelButton);

    expect(props.unselectRaffle).toHaveBeenCalled();
  });

});
