import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../scss/card.scss';
import AddIcon from '@mui/icons-material/Add';
import FavoriteIcon from '@mui/icons-material/Favorite';
import MediaPage from '../pages/MediaPage';
import user from '../helpers/apiUser';

function Card(props) {
  const isLoggedIn = localStorage.getItem('isLoggedIn');
  const { item } = props;
  const navigate = useNavigate();


  const handleLike = () => {
    user.get('/user/like', {
      params: {
        id: item.id,
      },
    })
      .then((res) => {
        console.log(res);
      }
      )
  };

  const handleWatchlist = () => {
  };

  const handleClick = () => {
    console.log(item);
    console.log("inside media card");
    // window.location.href = `/media/${item.id}`;
    console.log(item.contentId);
    navigate(`/media/${item.contentId}`);

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
