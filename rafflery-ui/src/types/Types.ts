export interface Raffle {
  id: string;
  name: string;
  item: Item;
  slotSize: number;
  purchasedTickets: Ticket[];
  createdBy: string;
}

export interface Item {
  name: string;
  value: number;
}

export interface Ticket {
  owner: string;
}

export interface UiConfig {
  environment: string;
}
