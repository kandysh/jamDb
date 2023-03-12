import React, { useState } from "react";
import api from "../helpers/api.jsx";
import '../scss/form.scss'
import Email from './form/Email';
import ShowAndHidePassword from "./form/ShowAndHidePassword.jsx";
import Username from './form/Username';

function Signup() {
    const isLoggedIn = localStorage.getItem('isLoggedIn');

    const [email, setEmail] = useState("");
    const [userName, setUsername] = useState("");
    const [password, setPassword] = useState("");


    const [emailValid, setEmailValid] = useState(false);
    const [usernameValid, setUsernameValid] = useState(false);
    const [passwordValid, setPasswordValid] = useState(false);

    const [FormError, setFormError] = useState(null);
    const [isFormValid, setIsFormValid] = useState(false);

    const handleEmailChange = (value, isValid) => {
        setEmail(event.target.value);
        setEmailValid(isValid);
    };

    const handleUsernameChange = (value, isValid) => {
        setUsername(event.target.value);
        setUsernameValid(isValid);
    };

    const handlePasswordChange = (value, isValid) => {
        setPassword(event.target.value);
        setPasswordValid(isValid);
    };

    const handleFormValidation = () => {
        setIsFormValid(emailValid && usernameValid && passwordValid);
    };

    const handleSubmit = (e) => {
        handleFormValidation();
        e.preventDefault();

        if (emailValid && usernameValid && passwordValid) {
            api
                .post("/auth/register", {
                    email,
                    userName,
                    password,
                })
                .then((response) => {
                    if (response.status === 200) {
                        localStorage.setItem('token', response.data.token);
                        localStorage.setItem('userName', userName);
                        localStorage.setItem('isLoggedIn', true);
                        setFormError("Sign up successful!");
                        window.location.href = "/";
                    } else {
                        setFormError('SignUp failed. Please check your username and password.');
                    }
                })
                .catch((error) => {
                    setFormError(error.response.data.message);
                    setIsFormValid(false);
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
                        disabled={isFormValid && isLoggedin ? true : false}
                        className="button form-submit"
                        tabIndex="4"
                        value="Create account"
                    />
                    {/* <input type="submit" className="button form-submit" tabIndex="6" /> */}
                </form>
            </div>
        </section>
    )
}

export default Signup    


