import { Button } from '@mui/material';
import axios from 'axios';
import { Link } from 'react-router-dom';
import user from '../helpers/apiUser';
import '../scss/Navbar.scss';

function Navbar() {
	const isLoggedIn = localStorage.getItem('isLoggedIn');
	const token = localStorage.getItem('token');

	const handleLogout = () => {
		localStorage.removeItem('isLoggedIn');
		localStorage.removeItem('token');
		localStorage.removeItem('userName');
		user.get('/auth/logout');
		window.location.href = '/';
	};

	const isLogin = () => {
		axios
			.get('/api/v1/auth')
			.then((res) => {
				console.log(res.data);
				if (res.data === true) {
					return true;
				} else {
					return false;
				}
			})
			.catch((err) => {
				console.log(err);
			});
	};

	return (
		<nav className="navbar navbar-expand-lg navbar-light bg-light">
			<Link to="/" className="navbar-brand">
				JamDb
			</Link>

			<div className="collapse navbar-collapse" id="navbarNav">
				<ul className="navbar-nav">
					{/* <li className="nav-item active">
                        <a className="nav-link" href="#">Home <span className="sr-only">(current)</span></a>
                    </li>    */}
					{isLoggedIn ? (
						<li className="nav-item">
							<a className="nav-link">
								<Button
									variant="contained"
									color="primary"
									onClick={handleLogout}
								>
									Logout
								</Button>
							</a>
						</li>
					) : (
						<>
							<li className="nav-item">
								<Link to="/login" className="nav-link">
									<Button variant="contained" color="primary">
										Login
									</Button>
								</Link>
							</li>
							<li className="nav-item">
								<Link to="/signup" className="nav-link">
									<Button variant="contained" color="primary">
										SignUp
									</Button>
								</Link>
							</li>
						</>
					)}
				</ul>
			</div>
		</nav>
	);
}

export default Navbar;
