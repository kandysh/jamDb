import React from 'react';
import { Link } from 'react-router-dom';
import logo from '../assets/404.png';
import  '../scss/grid.scss'

function NotFound() {
	return (
		<div className='grid-container'>
			<div >
				<Link to="/">
					{' '}
					<img src={logo} alt="404" />
				</Link>
			</div>
		</div>
	);
}

export default NotFound;
