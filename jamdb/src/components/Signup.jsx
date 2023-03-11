import React, { useState } from "react";
import axios from "axios";
import '../scss/form.scss'
import Email from './form/Email';
import ShowAndHidePassword from "./form/ShowAndHidePassword.jsx";
import Username from './form/Username';

function Signup() {

    const [email, setEmail] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");


    const [emailValid, setEmailValid] = useState(false);
    const [usernameValid, setUsernameValid] = useState(false);
    const [passwordValid, setPasswordValid] = useState(false);

    const [FormError, setFormError] = useState(null);
    const [isFormValid, setIsFormValid] = useState(false);



    const handleEmailChange = (value, isValid) => {
        setEmail(value);
        setEmailValid(isValid);
        console.log("email change" + email);
    };

    const handleUsernameChange = (value, isValid) => {
        setUsername(value);
        setUsernameValid(isValid);
        console.log("username change" + username);
    };

    const handlePasswordChange = (value, isValid) => {
        setPassword(value);
        setPasswordValid(isValid);
        console.log("password change" + password);
    };

    const handleFormValidation = () => {
        setIsFormValid(emailValid && usernameValid && passwordValid);

        console.log("email" + email);
        console.log("username" + username);
        console.log("password" + password);
    };

    const handleSubmit = (e) => {
        alert("You have successfully logged in");
        handleFormValidation();
        e.preventDefault();
        if (NameValid && emailValid && usernameValid && passwordValid) {
            axios
                .post("/api/signup", {
                    email,
                    username,
                    password,
                })
                .then((response) => {
                    alert("You have successfully logged in");
                    // do something with the response
                })
                .catch((error) => {
                    setFormError(error.response.data.message);
                });
        } else {
            setIsFormValid(false);
            setFormError("Please fill out all fields.");
        }
    };

    return (
        <section className="signup">
            <h2>Sign Up</h2>
            <div className="sign-in">
                <form action="sign-up-form" onSubmit={handleSubmit} acceptCharset='UTF-8' method='post'>
                    {FormError && <div>{FormError}</div>}

                    <div className="form-fields">
                        <Email index="1" onChange={handleEmailChange} />
                        <Username index="2" onChange={handleUsernameChange} />
                        <ShowAndHidePassword index="3" onChange={handlePasswordChange} />
                    </div>
                    <input
                        type="submit"
                        disabled={isFormValid}
                        className="button form-submit"
                        tabindex="4"
                        value="Create account"
                    />
                    {/* <input type="submit" className="button form-submit" tabIndex="6" /> */}
                </form>
            </div>
        </section>
    )
}

export default Signup    