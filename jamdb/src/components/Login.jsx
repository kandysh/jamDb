import React, { useState, useEffect } from 'react'
import '../scss/form.scss'
import Username from './form/Username';
import ShowAndHidePassword from './form/ShowAndHidePassword';
import axios from 'axios';

function Login() {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const [usernameValid, setUsernameValid] = useState(false);
    const [passwordValid, setPasswordValid] = useState(false);

    const [loginError, setLoginError] = useState(null);
    const [isFormValid, setIsFormValid] = useState(false);

    const handleUsernameChange = (value, isValid) => {
        if (isValid) {
            setUsername(value);
            axios.post("/api/v1/auth/checkuser/" + username, {
            }).then((response) => {
                if (response.data == "true") {
                    setUsernameValid(false);
                }
                else {
                    setUsernameValid(true);
                }
            })
                .catch((error) => {
                    console.log(error);
                });
        }

    };

    const handlePasswordChange = (value, isValid) => {
        setPassword(value);
        setPasswordValid(isValid);
    };


    const handleSubmit = (e) => {
        e.preventDefault();
        setIsFormValid(usernameValid && passwordValid);

        if (isFormValid) {
            axios.post("/api/v1/auth/login", { username, password })
                .then((response) => {
                    if (!response.ok) {
                        throw new Error("Invalid username or password");
                    }
                    return response.json();
                })
                .then((data) => {
                    const token = response.data.token;
                    localStorage.setItem("token", token);
                    window.location.href = '/'

                })
                .catch((error) => {
                    setLoginError(error.message);
                });
        } else {
            setIsFormValid(false);
            setLoginError("Please enter a valid username and password. / loginError");
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
                        disabled={isFormValid}
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