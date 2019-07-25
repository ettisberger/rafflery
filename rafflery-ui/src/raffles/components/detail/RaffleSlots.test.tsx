import { cleanup, render } from '@testing-library/react';
import { RaffleSlot, RaffleSlotProps } from './RaffleSlot';
import * as React from 'react';

describe('Raffle Slot', () => {
  const defaultProps: RaffleSlotProps = {
    slot: 1,
    sold: true,
  };

  afterEach(cleanup);

  it('should display the slot number', () => {
    const component = render(<RaffleSlot {...defaultProps} />);

    expect(component.getByText('1')).toBeDefined();
  });
});
