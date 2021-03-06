import axios from 'axios';

export const http = {
  async get(path: string) {
    const response = await axios.get(path, authConfig());
    return (response || {}).data;
  },

  async put(path: string, data: any) {
    const response = await axios.put(path, data, authConfig());
    return (response || {}).data;
  },

};

function authConfig() {
  const jwt = localStorage.getItem('id_token');

  if (!jwt) {
    return {};
  }

  return {
    headers: {
      Authorization: 'Bearer ' + jwt,
    },
  };
}
