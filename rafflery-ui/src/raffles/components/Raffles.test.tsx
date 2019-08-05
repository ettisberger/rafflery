import React from 'react';
import { Raffles, RafflesProps } from './Raffles';
import { someRaffles } from '../raffles.testData';
import { cleanup, fireEvent, render } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';

describe('Raffles Component', () => {
  const defaultProps: RafflesProps = {
    raffles: someRaffles,
    selectedRaffle: undefined,
    fetchRaffles: jest.fn(),
    selectRaffle: jest.fn(),
    unselectRaffle: jest.fn(),
  };

  afterEach(cleanup);

  it('should only display list of raffles when no raffle is selected', () => {
    const component = render(<Raffles {...defaultProps} />);

    const raffleTiles = component.getAllByTestId('raffle-item');
    expect(raffleTiles.length).toBe(someRaffles.length);
    expect(component.queryByTestId('raffle-detail')).toBeFalsy();
    raffleTiles.forEach(tile => expect(tile).not.toHaveClass('hidden'));
  });

  it('should call selectRaffle when a raffle is clicked', () => {
    const component = render(<Raffles {...defaultProps} />);

    const raffles = component.getAllByTestId('raffle-item');
    const raffleTileToSelect = raffles[0];

    fireEvent.click(raffleTileToSelect);

    expect(defaultProps.selectRaffle).toHaveBeenCalledWith(someRaffles[0].id);
  });

  it('should display detail information for raffle when selected', () => {
    const props = { ...defaultProps, selectedRaffle: someRaffles[0] };
    const component = render(<Raffles {...props} />);

    const slotPurchase = component.queryByTestId('raffle-detail');
    const detailBoxes = component.getAllByTestId('raffle-detail-box');
    expect(slotPurchase).toBeTruthy();
    expect(component.queryByText(`Purchase a slot for ${someRaffles[0].name}`));
    expect(detailBoxes.length).toBe(5);
    expect(detailBoxes[0]).toHaveTextContent(`${someRaffles[0].slotSize}SLOTS`);
    expect(detailBoxes[1]).toHaveTextContent(
      `${someRaffles[0].item.value}PRICE`
    );
  });

  it('should display slot items when raffle details are shown', () => {
    const props = { ...defaultProps, selectedRaffle: someRaffles[0] };
    const component = render(<Raffles {...props} />);

    const raffleSlotContainer = component.queryByTestId('raffle-slots');

    expect(raffleSlotContainer!!.children.length).toBe(someRaffles[0].slotSize);
  });

  it('should hide other raffles when one is selected', () => {
    const props = { ...defaultProps, selectedRaffle: someRaffles[0] };
    const component = render(<Raffles {...props} />);

    const raffleTiles = component.getAllByTestId('raffle-tile');
    expect(raffleTiles[0]).not.toHaveClass('hidden');
    raffleTiles.slice(1).forEach(tile => expect(tile).toHaveClass('hidden'));
  });

  it('should call unselectRaffle when purchase is cancelled', () => {
    const props = { ...defaultProps, selectedRaffle: someRaffles[0] };
    const component = render(<Raffles {...props} />);

    const cancelButton = component.getByText('Cancel');

    fireEvent.click(cancelButton);

    expect(props.unselectRaffle).toHaveBeenCalled();
  });
});
