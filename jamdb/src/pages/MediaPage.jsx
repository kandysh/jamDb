import React, { useEffect, useState } from "react";
import axios from "axios";
import CastRow from "../components/CastRow";
import "../scss/MediaPage.scss";
import CommentList from "../components/CommentList";

function MediaPage() {
    const [title, setTitle] = useState("");
    const [imagePath, setImagePath] = useState("");
    const [description, setDescription] = useState("");

    const style = (backgroundImagePath) => {
        const style = document.createElement('style');
        style.innerHTML = `
            .main {
                background-image: url(${backgroundImagePath});
            }
            

        `;
        document.head.appendChild(style);
    }

    useEffect(() => {
        axios.get("http://localhost:6969/api/media")
            .then(response => {
                setTitle(response.data.title);
                setImagePath(response.data.imagePath);
                setDescription(response.data.description);
                style(response.data.backgroundImagePath);
                // console.log(response.data);
            })
            .catch(error => {
                console.log(error);
            });
    }, []);


    return (
        <section>
            <div className="main" >
                <div className="box">
                    <div className="image-box">
                        <img src={imagePath} alt={title} />
                    </div>
                    <div className="content">
                        <h1>
                            {title}
                        </h1>
                        <p>
                            {description}
                        </p>
                    </div>
                </div>
            </div>
            <div className="space">

            </div>
            <div className="cast">
                <CastRow />
            </div>
            <section className="comment-section">
                <CommentList/>
            </section>

        </section>
    )
}

export default MediaPage