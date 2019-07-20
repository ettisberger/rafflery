import { Raffle } from '../types/Types';

export const aRaffle: Raffle = {
  id: '1',
  name: 'my raffle',
  createdBy: 'nicholas',
  item: { name: 'powerball', value: 2000 },
  purchasedTickets: [],
  slotSize: 100
};

export const someRaffles: Raffle[] = [aRaffle];
