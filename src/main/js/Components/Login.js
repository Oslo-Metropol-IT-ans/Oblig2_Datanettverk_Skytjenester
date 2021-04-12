import React from 'react';
import '../App.css';

const Login = () => {
    return (
        <div className="Login">
            <h2>Log in</h2>
            <form action="http://localhost:8080/login" type="post">
                <input name="username" type="text" placeholder="Username"></input>
                <input name="password" type="password" placeholder="Password"></input>
                <button type="submit">Logg inn</button>
            </form>
        </div>
    )
}

export default Login;
