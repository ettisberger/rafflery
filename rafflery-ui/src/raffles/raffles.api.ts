import {http} from '../http/http';
import {Raffle} from '../types/Types';

export async function fetchRaffles(): Promise<Raffle[]> {
    return await http.get('/api/raffles');
}

export async function purchaseSlots(raffleId: string, slots: number[]): Promise<undefined> {
    return await http.put(`/api/raffles/${raffleId}/slots`, { slots });
}
