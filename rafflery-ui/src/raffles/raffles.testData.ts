import { Raffle } from './types/Raffle';

export const someRaffles: Raffle[] = [{
  id: '1',
  name: 'my raffle',
  createdBy: 'nicholas',
  item: { name: 'powerball', value: 2000 },
  purchasedTickets: [],
  slotSize: 100
}];
