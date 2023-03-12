import axios from 'axios';

export const setAuthToken = token => {

    const token1 = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzaGl2YW0iLCJpYXQiOjE2Nzg1MzIxMzYsImV4cCI6MTY3ODU0MjIxNn0.KK1_2YlCGT5KZs5og0WiPBa2XAQYuXNB77y6u5Rhv20"
    if (token1) {
        axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
    }
    else
        delete axios.defaults.headers.common["Authorization"];
}