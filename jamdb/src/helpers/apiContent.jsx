import axios from 'axios';

export default axios.create({
	baseURL: 'https://jamapi.kandysh.xyz/api/v2',
	headers: {
		'Content-Type': 'application/json',
	},
});
