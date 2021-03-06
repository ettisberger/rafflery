import React from 'react';
import { RaffleSlot } from './RaffleSlot';
import './RaffleSlots.css';

export interface RaffleSlotsProps {
  maxSlots: number;
  soldSlots: number[];
  onSlotChange: (slots: number[]) => void;
}

export interface RaffleSlotsState {
  slots: RaffleSlotItem[];
}

enum RaffleSlotState {
  SELECTED,
  SOLD,
  FREE,
}

export class RaffleSlotItem {
  public slot: number;
  public status: RaffleSlotState;

  constructor(slot: number, status: RaffleSlotState) {
    this.slot = slot;
    this.status = status;
  }

  public isSelected = () => {
    return this.status === RaffleSlotState.SELECTED;
  };

  public isSold = () => {
    return this.status === RaffleSlotState.SOLD;
  };

  public isFree = () => {
    return this.status === RaffleSlotState.FREE;
  };
}

export class RaffleSlots extends React.Component<
  RaffleSlotsProps,
  RaffleSlotsState
> {
  constructor(props: any) {
    super(props);

    this.state = { slots: [] };
  }

  componentDidMount() {
    const slots: RaffleSlotItem[] = [];

    for (let i = 1; i <= this.props.maxSlots; i++) {
      if (this.props.soldSlots.includes(i)) {
        slots[i - 1] = new RaffleSlotItem(i, RaffleSlotState.SOLD);
      } else {
        slots[i - 1] = new RaffleSlotItem(i, RaffleSlotState.FREE);
      }
    }

    this.setState({ slots });
  }

  onSelect = (raffleSlot: RaffleSlotItem) => {
    const slots = this.state.slots;

    if (raffleSlot.isSelected()) {
      raffleSlot.status = RaffleSlotState.FREE;
    } else if (raffleSlot.isFree()) {
      raffleSlot.status = RaffleSlotState.SELECTED;
    }

    slots[raffleSlot.slot - 1] = raffleSlot;

    this.setState({ slots });

    this.props.onSlotChange(
      slots.filter(slot => slot.isSelected()).map(slot => slot.slot)
    );
  };

  render() {
    return (
      <div className="raffle-slots" data-testid="raffle-slots">
        {this.state.slots.map(raffleSlot => (
          <RaffleSlot
            sold={raffleSlot.isSold()}
            selected={raffleSlot.isSelected()}
            slot={raffleSlot.slot}
            key={raffleSlot.slot}
            onSelect={() => this.onSelect(raffleSlot)}
          />
        ))}
      </div>
    );
  }
}
