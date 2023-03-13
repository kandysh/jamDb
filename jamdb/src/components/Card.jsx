import '../scss/card.scss';
import AddIcon from '@mui/icons-material/Add';
import FavoriteIcon from '@mui/icons-material/Favorite';
import placeHolderImg from '../assets/placeholder.jpg';
import { Link } from 'react-router-dom';

function Card(props) {
	const isLoggedIn = localStorage.getItem('isLoggedIn');
	const { item } = props;
	const handleLike = () => {
		// user.get('/user/like', {item.id})
		//   .then((res) => {
		//     console.log(res);
		//   })
	};

	const handleWatchlist = () => {
		// user.post('/user/watchlist',
	};

	return (
		<div className="card  ">
			<div className="image-container">
				<Link to={'/content/' + item.contentId}>
					<img
						src={item.picture}
						alt={item.title}
						onError={(e) => (e.target.src = placeHolderImg)}
					/>
				</Link>
			</div>
			<div className="card-content">
				<h2>{item.title}</h2>
				<div className="actions">
					{isLoggedIn ? (
						<>
							<button onClick={handleLike} className="favourite">
								<FavoriteIcon />
							</button>
							<button onClick={handleWatchlist} className="addicon">
								<AddIcon />
							</button>
						</>
					) : (
						<></>
					)}
				</div>
			</div>
		</div>
	);
}

export default Card;
