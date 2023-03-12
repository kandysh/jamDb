import axios from 'axios';
const authToken = localStorage.getItem('token');

const user = axios.create({
  // baseURL: 'http://localhost:3030/api/v1/',
  baseURL: 'https://jamapi.kandysh.xyz/api/v1/',
    headers: {
      Authorization: `Bearer ${authToken}`,
      'Content-Type': 'application/json',
    },
});

export default user;