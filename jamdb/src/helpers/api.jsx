import axios from 'axios';
const authToken = localStorage.getItem('token');
const api = axios.create({
  baseURL: 'https://jamapi.kandysh.xyz/api/v2',
  headers: {
    'Content-Type': 'application/json',
  },
});

const user = axios.create({
  baseURL: 'https://jamapi.kandysh.xyz/api/v2',
    headers: {
      Authorization: `Bearer ${authToken}`,
      'Content-Type': 'application/json',
    },
});

export default api;