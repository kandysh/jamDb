import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import "../scss/MediaPage.scss";

function MediaPage({ item }) {
    const { contentId } = useParams();

    return (
        <section>
            <div className="tags">
                <span class="inline-block">display: inline-block</span>
                {contentId}
            </div>
        </section>
    )
}

export default MediaPage