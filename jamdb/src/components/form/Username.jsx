import React, { useState, useEffect } from "react";

function Username(props) {
    const [username, setUsername] = useState("");
    const [isUsernameValid, setIsUsernameValid] = useState(false);
    const [usernameError, setUsernameError] = useState("");
    const [usernameExistsError, setUsernameExistsError] = useState("");
    const [isCheckingUsername, setIsCheckingUsername] = useState(false);

    const handleUsernameChange = (e) => {
        const value = e.target.value;
        setUsername(value);

        // Validate username on every change
        const regex = /^[a-zA-Z][a-zA-Z0-9_]{3,}$/;
        setIsUsernameValid(regex.test(value));
        setUsernameError(isUsernameValid ? "" : "Username must start with an alphabet and contain only alphabets, underscores, and numbers");
        props.onChange(username, isUsernameValid);
    };

    // useEffect(() => {
    //     if (username) {
    //         // Check if username exists on API
    //         setIsCheckingUsername(true);
    //         fetch(`https://example.com/api/check-username/${username}`)
    //             .then(response => response.json())
    //             .then(data => {
    //                 setIsCheckingUsername(false);
    //                 if (data.exists) {
    //                     setUsernameExistsError("Username already exists. Please choose a different one.");
    //                 } else {
    //                     setUsernameExistsError("");
    //                 }
    //             })
    //             .catch(error => {
    //                 setIsCheckingUsername(false);
    //                 console.error(error);
    //             });
    //     }
    // }, [username]);

    return (
        <div>
            <fieldset>
                {/* <label htmlFor="username" className="username">Username</label> */}
                {/* <input type="text" className="text-input" name="username" id="username" placeholder="Username" tabindex="1" /> */}
            

            <label htmlFor="username" className="username">Username</label>
            <input
                type="text"
                id="username"
                className="text-input"
                name="username" 
                placeholder="Username"
                value={username}
                onChange={handleUsernameChange}
                tabIndex={props.index}
            />
            {usernameError && <div>{usernameError}</div>}
            {usernameExistsError && <div>{usernameExistsError}</div>}
            {isCheckingUsername && <div>Checking username...</div>}
            </fieldset>
        </div>
    );
}

export default Username;
