import React, { useState } from 'react'
import '../scss/form.scss'
import Username from './form/Username';
import ShowAndHidePassword from './form/ShowAndHidePassword';

function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const [usernameValid, setUsernameValid] = useState(false);
    const [passwordValid, setPasswordValid] = useState(false);

    const [loginError, setLoginError] = useState(null);
    const [isFormValid, setIsFormValid] = useState(false);

    const handleUsernameChange = (value, isValid) => {
        setUsername(value);
        setUsernameValid(isValid);

    };

    const handlePasswordChange = (value, isValid) => {
        setPassword(value);
        setPasswordValid(isValid);
    };


    const handleSubmit = (e) => {
        e.preventDefault();
        setIsFormValid(usernameValid && passwordValid);

        if (isFormValid) {
            fetch("/api/login", {
                method: "POST",
                body: JSON.stringify({ username, password }),
                headers: {
                    "Content-Type": "application/json",
                },
            })
                .then((response) => {
                    if (!response.ok) {
                        throw new Error("Invalid username or password");
                    }
                    return response.json();
                })
                .then((data) => {
                    alert("You have successfully logged in");
                    // do something with the login response
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
            <h2>Sign in to Anime-List</h2>
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