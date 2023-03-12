import React, { useState, useEffect } from 'react';
import Card from './Card';
import api from '../helpers/api';
import '../scss/grid.scss'

function Grid() {
  const [items, setItems] = useState([]);

  useEffect(() => {
    api.get('/content/list')
      .then(response => {
        setItems(response.data);
        console.log(response.data);
      })
      .catch(error => {
        console.error(error);
      });
  }, []);

  return (
    <section className="grid-container">
      <div className="grid">
        {items.map(item => (
          <Card key={item.id} item={item} />
        ))}
      </div>
    </section>
  );
}

export default Grid;
