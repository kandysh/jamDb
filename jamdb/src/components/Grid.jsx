import React, { useState, useEffect } from 'react';
import Card from './Card';
import api from '../helpers/api';
import '../scss/grid.scss';

function Grid() {
	const [items, setItems] = useState([]);

	useEffect(() => {
		const fetchAnime = async () => {
			await api
				.get('/content/list')
				.then((response) => {
					setItems(response.data);
				})
				.catch((error) => {
					console.log(error);
				});
		};
		fetchAnime();
	}, []);

	return (
		<section className="grid-container">
			<div className="grid">
				{items.map((item) => (
						<Card item={item} key={item.contentId}/>
				))}
			</div>
		</section>
	);
}

export default Grid;
