import { http } from '../http/http';
import { Raffle } from '../types/Types';

export async function fetchRaffles(): Promise<Raffle[]> {
  return await http.get('/api/raffles');
}
