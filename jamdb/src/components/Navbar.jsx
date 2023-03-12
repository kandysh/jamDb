import { Button } from '@mui/material';
import axios from 'axios';
import React from 'react';
import { BrowserRouter, Link } from 'react-router-dom';
import '../scss/Navbar.scss';


function Navbar() {
    const isLogin = () => {
        axios.get("/api/v1/auth")
            .then((res) => {
                console.log(res.data);
                if (res.data === true) {
                    return true;
                } else {
                    return false;
                }
            }
            )
            .catch((err) => {
                console.log(err);
            }
            )

    };

    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <a className="navbar-brand">
                <Link to="/">
                    JamDb
                </Link>
            </a>

            <div className="collapse navbar-collapse" id="navbarNav">
                <ul className="navbar-nav">
                    {/* <li className="nav-item active">
                        <a className="nav-link" href="#">Home <span className="sr-only">(current)</span></a>
                    </li>    */}
                    <li className="nav-item">
                        <a className="nav-link" href="#">
                            <Link to="/login">
                                <Button variant="contained" color="primary">Login</Button>
                            </Link>
                        </a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#">
                            <Link to="/signup">
                                <Button variant="contained" color="primary">SignUp</Button>
                            </Link>
                        </a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#">
                            <Link to="/logout">
                                <Button variant="contained" color="primary">Logout</Button>
                            </Link>
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
    );

}

export default Navbar;