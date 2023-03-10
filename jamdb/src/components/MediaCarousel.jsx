import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ArrowForwardIosRoundedIcon from '@mui/icons-material/ArrowForwardIosRounded';
import ArrowBackIosRoundedIcon from '@mui/icons-material/ArrowBackIosRounded';
import '../scss/MediaCarousel.scss';

function MediaCarousel() {
    const [mediaItems, setMediaItems] = useState([]);
    const [currentItemIndex, setCurrentItemIndex] = useState(0);

    useEffect(() => {
        axios.get('https://example.com/api/media')
            .then(response => {
                setMediaItems(response.data);
            })
            .catch(error => {
                console.error('Error fetching media items:', error);
            });
    }, []);

    useEffect(() => {
        const timer = setInterval(() => {
            setCurrentItemIndex(currentItemIndex => (currentItemIndex + 1) % mediaItems.length);
        }, 5000);

        return () => clearInterval(timer);
    }, [mediaItems]);

    const currentItem = mediaItems[currentItemIndex];

    const handlePrevClick = () => {
        setCurrentItemIndex(currentItemIndex => (currentItemIndex + mediaItems.length - 1) % mediaItems.length);
    };

    const handleNextClick = () => {
        setCurrentItemIndex(currentItemIndex => (currentItemIndex + 1) % mediaItems.length);
    };

    const renderMediaItem = (mediaItem, index) => {
        const isActive = index === currentItemIndex;
        return (
            <button key={index} className={`media-item ${isActive ? 'active' : ''}`} onClick={() => setCurrentItemIndex(index)}>
                <img src={mediaItem.imageUrl} alt={mediaItem.title} />
            </button>
        );
    };


    return (
        <div className="media-carousel " >
            <div className="blur-box">
                <div className="carousel-content">
                    <button className="prev-button" onClick={handlePrevClick}><ArrowBackIosRoundedIcon sx={{ fontSize: 40 }}/></button>
                    <img src="https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx143338-zhyDVYgEzsm5.png" alt="test" />
                    {/* <img src={currentItem.imageUrl} alt={currentItem.title} /> */}
                    {/* <h2>{currentItem.title}</h2>
                    <p>{currentItem.description}</p> */}
                    <h2>One Piece</h2>
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit.</p>
                    <button className="next-button" onClick={handleNextClick}><ArrowForwardIosRoundedIcon sx={{ fontSize: 40 }} /></button>
                </div>
                <div className="media-list">
                    {mediaItems.map(renderMediaItem)}
                </div>
            </div>
        </div>
    );
}
export default MediaCarousel;
