import React from 'react';

function MediaCard(props) {
  const { title, imageUrl, description } = props;

  return (
    <div className="media-card">
      <img src="https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx143338-zhyDVYgEzsm5.png" alt="One Piece" />
      <h2>{title}</h2>
      <p>{description}</p>
    </div>
    
  );
}

export default MediaCard;