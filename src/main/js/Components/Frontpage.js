import React from 'react';
import '../App.css';
import {Link} from 'react-router-dom'

const Frontpage = () => {
    return (
        <div className="Frontpage">
            <div className="buttons">
                <Link to="/login">
                    <button>Log in</button>
                </Link>
                <Link to="/register">
                    <button>Register</button>
                </Link>
            </div>
            
        </div>
    );
}

export default Frontpage;
