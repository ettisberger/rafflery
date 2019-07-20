import { http } from '../http/http';

export async function fetchUiConfig() {
  return await http.get('/api/ui-config');
}
