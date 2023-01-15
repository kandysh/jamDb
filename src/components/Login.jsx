import React from 'react'
import '../css/form.css'

function Login() {
    return (
        <section className="login">
            <h2>Sign in to Anime-List</h2>
            <div className="sign-in">
                <form action="sign-in-form" acceptCharset='UTF-8' method='post'>
                    <div className="form-fields">
                        <fieldset>
                            <label htmlFor="username" className="username">Username</label>
                            <input type="text" className="text-input" name="username" id="username" placeholder="Username" tabindex="1"/>
                        </fieldset>
                        <fieldset>
                            <label htmlFor="password" className="password">Password</label>
                            <input type="password" className="text-input"  name="password" id="password" placeholder="Password" tabindex="2" />
                        </fieldset>
                    </div>
                            <input type="submit" className="button form-submit" tabindex="3" />
                </form>
            </div>
        </section>
    )
}

export default Login    