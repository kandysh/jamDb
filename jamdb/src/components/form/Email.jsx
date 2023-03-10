import React, { useState, useEffect } from "react";

function Email(props) {
    const [email, setEmail] = useState("");
    const [isEmailValid, setIsEmailValid] = useState(false);
    const [emailError, setEmailError] = useState("");
    const [emailExistsError, setEmailExistsError] = useState("");
    const [isCheckingEmail, setIsCheckingEmail] = useState(false);

    const handleEmailChange = (e) => {
        const value = e.target.value;
        setEmail(value);

        // Validate email on every change
        const regex = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
        setIsEmailValid(regex.test(value));
        setEmailError(isEmailValid ? "" : "Please enter a valid email address");
        props.onChange(email, isEmailValid);
    };

    useEffect(() => {
        if (email) {
            // Check if email exists on API
            setIsCheckingEmail(true);
            fetch(`https://example.com/api/check-email/${email}`)
                .then(response => response.json())
                .then(data => {
                    setIsCheckingEmail(false);
                    if (data.exists) {
                        setEmailExistsError("Email already exists. Please choose a different one.");
                    } else {
                        setEmailExistsError("");
                    }
                })
                .catch(error => {
                    setIsCheckingEmail(false);
                    console.error(error);
                });
        }
    }, [email]);

    return (
        <div>
            <fieldset>
                {/* <label htmlFor="email" className="email">Email</label> */}
                {/* <input type="text" className="text-input" name="email" id="email" placeholder="Email" tabIndex="1" /> */}

                <label htmlFor="email" className="email">Email</label>
                <input
                    type="text"
                    id="email"
                    className="text-input"
                    name="email"
                    placeholder="Email"
                    value={email}
                    onChange={handleEmailChange}
                    tabIndex={props.index}
                />
                {emailError && <div>{emailError}</div>}
                {emailExistsError && <div>{emailExistsError}</div>}
                {isCheckingEmail && <div>Checking email...</div>}
            </fieldset>
        </div>
    );
}

export default Email;
