import React, { useState, useEffect } from 'react'
import '../scss/form.scss'
import Username from './form/Username';
import ShowAndHidePassword from './form/ShowAndHidePassword';
import axios from 'axios';
import api from '../helpers/api';

function Login() {

    const [userName, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const [usernameValid, setUsernameValid] = useState(false);
    const [passwordValid, setPasswordValid] = useState(false);

    const [loginError, setLoginError] = useState(null);
    const [isFormValid, setIsFormValid] = useState(false);

    const handleUsernameChange = (value, isValid) => {
        setUsername(event.target.value);
        setUsernameValid(isValid);
        handleFormValidation();
    };

    const handlePasswordChange = (value, isValid) => {
        setPassword(event.target.value);
        setPasswordValid(isValid);
        handleFormValidation();
    };

    const handleFormValidation = () => {
        if (usernameValid && passwordValid)
            setIsFormValid(true);
        else
            setIsFormValid(false);
    };

    const handleSubmit = (e) => {
        handleFormValidation();
        e.preventDefault();
        console.log('inside submit!');
        if (isFormValid) {
            api.post("/auth/login", { userName, password })
                .then((response) => {
                    console.log(response);
                    if (response.status === 200) {
                        console.log("inside 200")
                        localStorage.setItem('token', response.data.token);
                        localStorage.setItem('userName', userName);
                        localStorage.setItem('isLoggedIn', true);
                        setLoginError("Login successful ⚡⚡  ✔");
                        window.location.href = '/';
                    } else {
                        setFormError('Login failed. Please check your userName and password.');
                    }
                })
                .catch((error) => {
                    setLoginError(error.message);
                });
        } else {
            setIsFormValid(false);
            setLoginError("Please enter a valid userName and password. / loginError");
        }
    };


    return (
        <section className="login">
            <h2>Login </h2>
            <div className="sign-in">
                <form action="sign-in-form" onSubmit={handleSubmit} acceptCharset='UTF-8' method='post'>
                    {loginError && <div>{loginError}</div>}

                    <div className="form-fields">
                        <Username index="1" onChange={handleUsernameChange} />
                        <ShowAndHidePassword index="2" onChange={handlePasswordChange} />
                    </div>

                    <input
                        type="submit"
                        disabled={!isFormValid}
                        className="button form-submit"
                        tabIndex="3"
                        value="Login"
                    />
                </form>
            </div>
        </section>
    )
}

export default Login    