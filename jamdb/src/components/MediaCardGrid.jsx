import React from 'react';
import MediaCard from './MediaCard';

function MediaCardGrid() {
  const mediaCards = [
    { title: 'Card 1', imageUrl: 'https://example.com/card1.jpg', description: 'This is card 1.' },
    { title: 'Card 2', imageUrl: 'https://example.com/card2.jpg', description: 'This is card 2.' },
    { title: 'Card 3', imageUrl: 'https://example.com/card3.jpg', description: 'This is card 3.' },
  ];

  return (
    <div className="media-card-grid">
      {mediaCards.map(card => <MediaCard key={card.title} {...card} />)}
    </div>
  );
}

export default MediaCardGrid;