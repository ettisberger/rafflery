import { cleanup, render } from '@testing-library/react';
import * as React from 'react';
import { RaffleSlots, RaffleSlotsProps } from './RaffleSlots';
import '@testing-library/jest-dom/extend-expect';

describe('Raffle Slot', () => {
  const defaultProps: RaffleSlotsProps = {
    maxSlots: 10,
    soldSlots: [5, 6],
  };

  afterEach(cleanup);

  it('should display slot elements', () => {
    const component = render(<RaffleSlots {...defaultProps} />);

    const slotElements = component.getAllByTestId('slot');

    expect(slotElements.length).toBe(defaultProps.maxSlots);

    slotElements.forEach((slot, i) => {
      expect(slot).toHaveAttribute('unselectable');
      if (defaultProps.soldSlots.includes(i)) {
        expect(slot).toHaveClass('sold');
      } else {
        expect(slot).not.toHaveClass('sold');
      }
    });
  });
});
