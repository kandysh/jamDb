import React, { useState } from 'react';
import Visibility from '@mui/icons-material/Visibility';
import VisibilityOff from '@mui/icons-material/VisibilityOff';
import '../../scss/form.scss';
function ShowAndHidePassword(props) {

    const [showPassword, setShowPassword] = useState(false);
    const [passwordInput, setPasswordInput] = useState("");
    const [errorMessage, setErrorMessage] = useState("");
    const [isValid, setIsValid] = useState(false);

    const passwordRegex = /^[a-zA-Z0-9_]{8,20}$/;



    const handlePasswordChange = (evnt) => {
        setPasswordInput(evnt.target.value);
        const isValid = passwordRegex.test(passwordInput);
        setIsValid(isValid);

        if (passwordRegex.test(e.target.value)) {
            setErrorMessage(
                "Password must be between 8 and 20 characters and only contain alphanumeric characters and underscore"
            );
        } else {
            setErrorMessage("");
        }
        props.onChange(passwordInput, isValid);
    }
    const togglePassword = () => {
        if (showPassword === false) {
            setShowPassword(true)
        }
        else {
            setShowPassword(false)
        }
    }
    const handlePasswordBlur = () => {
        const isValid = passwordRegex.test(passwordInput);
        setIsValid(isValid);
        setShowPassword(false);

    };








    return (
        <fieldset password>
            <label htmlFor="password" className="password">Password</label>
            <div className="password_div">
                <input
                    type={showPassword ? "text" : "password"}
                    onChange={handlePasswordChange}
                    onBlur={handlePasswordBlur}
                    value={passwordInput}
                    className="text-input"
                    name="password"
                    id="password"
                    placeholder="Password"
                    tabIndex={props.index} />

                <div className="text-input" onClick={togglePassword}>
                    {showPassword ? <VisibilityOff /> : <Visibility />}
                </div>

                {isValid ?
                    <div>dfdavadvda </div>
                    :
                    <div> {errorMessage} </div>
                }
            </div>

        </fieldset>

    )

}
export default ShowAndHidePassword;