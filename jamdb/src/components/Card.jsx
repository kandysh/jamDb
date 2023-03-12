import React from 'react';
import '../scss/card.scss';
import AddIcon from '@mui/icons-material/Add';
import FavoriteIcon from '@mui/icons-material/Favorite';
import MediaPage from '../pages/MediaPage';
import user from '../helpers/apiUser';

function Card(props) {
  const isLoggedIn = localStorage.getItem('isLoggedIn');
  const { item } = props;


  const handleLike = () => {
    user.get('/user/like', {item.id})
      .then((res) => {
        console.log(res);
      })
  };

  const handleWatchlist = () => {
    user.post('/user/watchlist',
  };

  const handleClick = () => {
    console.log(item);
    console.log("inside media card");
    return <MediaPage item={item} />;

  };



  return (
    <div className="card  ">
      <div
        className="image-container"
        style={{ backgroundImage: `url(${item.picture})` }}
        onClick={handleClick}
      >
        <img src={item.picture} alt={item.title} />
      </div>
      <div className="card-content">
        <h2>{item.title}</h2>
        <div className="actions">
          {isLoggedIn ? (
            <>
              <button onClick={handleLike} className="favourite">
                <FavoriteIcon />
              </button>
              < button onClick={handleWatchlist} className="addicon">
                <AddIcon />
              </button>
            </>
          ) :
            (<></>)}
        </div>
      </div>
    </div >
  );
}

export default Card;
