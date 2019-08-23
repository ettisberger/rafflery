import { cleanup, render } from '@testing-library/react';
import RaffleTile, { RaffleTileProps } from './RaffleTile';
import { aRaffle } from '../../../raffles.testData';
import * as React from 'react';

describe('Raffle Tile', () => {
  const raffle = { ...aRaffle };

  const defaultProps: RaffleTileProps = {
    color: 'blue',
    raffle,
    hidden: false,
    onSelect: jest.fn(),
  };

  afterEach(cleanup);

  it('should display the raffle info', () => {
    const component = render(<RaffleTile {...defaultProps} />);

    const numberOfPurchasesSlots = raffle.purchasedTickets.length;
    const numberOfSlots = raffle.item.value / raffle.slotSize;

    expect(component.getByText(aRaffle.name)).toBeDefined();

    expect(component.getByTestId('raffle-detail-slot-size').innerHTML).toBe(
      `CHF ${aRaffle.slotSize.toLocaleString()}.00`
    );

    expect(component.getByTestId('raffle-detail-total-value').innerHTML).toBe(
      `CHF ${aRaffle.item.value.toLocaleString()}.00`
    );

    expect(component.getByTestId('raffle-detail-sold-slots').innerHTML).toBe(
      `${numberOfPurchasesSlots} / ${numberOfSlots}`
    );
  });
});
