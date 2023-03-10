import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../scss/CastRow.scss';


const CastRow = () => {
  const [title, setTitle] = useState('');
  const [cast, setCast] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      const response = await axios.get('http://localhost:6969/api/media');
      setCast(response.data.cast);
      setTitle(response.data.title);
    };

    fetchData();
  }, []);

  return (
    <div className="cast-row">
      <h2 className="cast-row-title">
        Cast  
        <span className="title">
          {title}
        </span>
      </h2>
      <div className="cast-row-members">
        {cast.map(member => (
          <div key={member} className="cast-member">
            <div className="cast-image-container">
              <img src={member.imagePath} alt={member.name} className="cast-image" />
            </div>
            <p className="cast-name">{member.name}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default CastRow;
