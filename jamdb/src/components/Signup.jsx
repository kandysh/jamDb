import React from 'react'
import '../scss/form.scss'
import ShowAndHidePassword from "./form/ShowAndHidePassword.jsx";

function Signup() {
    return (
        <section className="signup">
            <h2>Sign Up to Anime-List</h2>
            <div className="sign-in">
                <form action="sign-up-form" acceptCharset='UTF-8' method='post'>
                    <div className="form-fields">
                        <div className="name_field">
                            <fieldset>
                                <label htmlFor="firstname" className="firstname">Firstname</label>
                                <input type="text" className="text-input" name="firstname" id="firstname" placeholder="Firstname" tabindex="1" />
                            </fieldset>
                            <fieldset>
                                <label htmlFor="lastname" className="lastname">Lastname</label>
                                <input type="text" className="text-input" name="lastname" id="lastname" placeholder="Lastname" tabindex="2" />
                            </fieldset>
                        </div>
                        <fieldset>
                            <label htmlFor="email" className="email">Email</label>
                            <input type="email" className="text-input" name="email" id="email" placeholder="Email" tabindex="3" />
                        </fieldset>
                        <fieldset>
                            <label htmlFor="username" className="username">Username</label>
                            <input type="text" className="text-input" name="username" id="username" placeholder="Username" tabindex="4" />
                        </fieldset>
                        {/*<fieldset>*/}
                        {/*    <label htmlFor="password" className="password">Password</label>*/}
                        {/*    <input type="password" className="text-input" name="password" id="password" placeholder="Password" tabindex="5" />*/}
                        {/*</fieldset>*/}
                        <ShowAndHidePassword index="5" />
                    </div>
                    <input type="submit" className="button form-submit" tabindex="6" />
                </form>
            </div>
        </section>
    )
}

export default Signup    