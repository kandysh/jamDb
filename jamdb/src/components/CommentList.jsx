import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../scss/CommentList.scss';

function CommentList() {
  const [comments, setComments] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:6969/api/media')
      .then(response => {
        setComments(response.data.comments);
        console.log(response.data.comments);
      })
      .catch(error => {
        console.log(error);
      });
  }, []);

  return (
    <div className="comments">
      {comments.map(comment => (
        <div key={comment.userid} className="comment">
          <h2>{comment.name}</h2>
          <p>{comment.comment}</p>
        </div>
      ))}
    </div>
  );
}

export default CommentList;
