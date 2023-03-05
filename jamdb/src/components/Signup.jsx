import React, { useState } from "react";
import axios from "axios";
import '../scss/form.scss'
import Email from './form/Email';
import ShowAndHidePassword from "./form/ShowAndHidePassword.jsx";
import Username from './form/Username';

function Signup() {
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const [NameValid, setNameValid] = useState(false);
    const [emailValid, setEmailValid] = useState(false);
    const [usernameValid, setUsernameValid] = useState(false);
    const [passwordValid, setPasswordValid] = useState(false);

    const [FormError, setFormError] = useState(null);
    const [isFormValid, setIsFormValid] = useState(false);

    const handleNameChange = (evnt) => {
        setFirstName(evnt.target.firstname);
        setLastName(evnt.target.lastname);
        setNameValid(isValid);
        console.log("name change" + firstName);
        console.log("name change" + lastName);
    };

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
        setIsFormValid(NameValid && emailValid && usernameValid && passwordValid);

        console.log("fistname" + firstName);
        console.log("lastname" + lastName);
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
                    firstName,
                    lastName,
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
            <h2>Sign Up to Anime-List</h2>
            <div className="sign-in">
                <form action="sign-up-form" onSubmit={handleSubmit} acceptCharset='UTF-8' method='post'>
                    {FormError && <div>{FormError}</div>}

                    <div className="form-fields">
                        <div className="name_field">
                            <fieldset>
                                <label htmlFor="firstname" className="firstname">Firstname</label>
                                <input
                                    type="text"
                                    onSubmit={handleNameChange}
                                    className="text-input"
                                    name="firstname" 
                                    id="firstname"
                                    placeholder="First Name"
                                    tabIndex="1"
                                />
                            </fieldset>
                            <fieldset>
                                <label htmlFor="lastname" className="lastname">Lastname</label>
                                <input
                                    type="text"
                                    onSubmit={handleNameChange}
                                    className="text-input"
                                    name="lastname"
                                    id="lastname"
                                    placeholder="Last Name"
                                    tabIndex="2"
                                />
                            </fieldset>
                        </div>
                        <Email index="3" onChange={handleEmailChange} />
                        <Username index="4" onChange={handleUsernameChange} />
                        <ShowAndHidePassword index="5" onChange={handlePasswordChange} />
                    </div>
                    <input
                        type="submit"
                        disabled={isFormValid}
                        className="button form-submit"
                        tabindex="6"
                        value="Create account"
                    />
                    {/* <input type="submit" className="button form-submit" tabIndex="6" /> */}
                </form>
            </div>
        </section>
    )
}

export default Signup    