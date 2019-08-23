import React from 'react';
import { cleanup, render, fireEvent } from '@testing-library/react';
import { RaffleDetail, RaffleDetailProps } from './RaffleDetail';
import { aRaffle } from '../../raffles.testData';
import '@testing-library/jest-dom/extend-expect';

describe('RaffleDetail', () => {
  afterEach(cleanup);

  const defaultProps: RaffleDetailProps = {
    raffle: { ...aRaffle },
    fetchRaffles: jest.fn(),
    purchaseSlots: jest.fn(),
  };

  it('purchase should be disabled when no slots are selected', () => {
    const component = render(<RaffleDetail {...defaultProps} />);

    const purchaseButton = component.getByTestId('purchase-button');

    expect(purchaseButton).toHaveAttribute('disabled', '');
  });

  it('should call purchase slots when purchase button is clicked', () => {
    const component = render(<RaffleDetail {...defaultProps} />);

    const slots = component.getAllByTestId('slot');
    const purchaseButton = component.getByTestId('purchase-button');
    const slotToPurchase = slots[0];

    fireEvent.click(slotToPurchase);
    fireEvent.click(purchaseButton);

    expect(defaultProps.purchaseSlots).toHaveBeenCalledWith(
      defaultProps.raffle,
      [1]
    );
  });
});
