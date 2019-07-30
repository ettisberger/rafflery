import { cleanup, render } from '@testing-library/react';
import { RaffleSlot, RaffleSlotProps } from './RaffleSlot';
import * as React from 'react';
import '@testing-library/jest-dom/extend-expect';

describe('Raffle Slot', () => {
  const defaultProps: RaffleSlotProps = {
    slot: 1,
    selected: false,
    sold: false,
    onSelect: jest.fn(),
  };

  afterEach(cleanup);

  it('should display the slot number', () => {
    const component = render(<RaffleSlot {...defaultProps} />);

    expect(component.getByText('1')).toBeDefined();
  });

  it('sold slot should be marked', () => {
    const props = { ...defaultProps, sold: true };
    const component = render(<RaffleSlot {...props} />);

    expect(component.getByText('1')).toBeDefined();
    expect(component.getByTestId('slot')).toHaveClass('sold');
  });

  it('clicked slot should be marked', () => {
    const props = { ...defaultProps, selected: true };
    const component = render(<RaffleSlot {...props} />);

    expect(component.getByText('1')).toBeDefined();
    expect(component.getByTestId('slot')).toHaveClass('selected');
  });
});
