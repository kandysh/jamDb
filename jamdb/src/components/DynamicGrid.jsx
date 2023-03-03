import React, { useState, useEffect } from 'react';
import axios from 'axios';

function DynamicGrid() {
  const [gridElements, setGridElements] = useState([]);

  useEffect(() => {
    axios.get('https://example.com/api/gridElements')
      .then(response => {
        setGridElements(response.data.map(element => <div key={element.id} className="grid-element">{element.name}</div>));
      })
      .catch(error => {
        console.error('Error fetching grid elements:', error);
      });
  }, []);

  return (
    <div className="grid-container">
      {gridElements}
    </div>
  );
}

export default DynamicGrid;
