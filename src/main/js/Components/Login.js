import React from 'react';
import '../App.css';

const Login = () => {
    return (
        <div className="Login">
            <h2>Log in</h2>
            <form action="http://localhost:8080/login" method="post">
                <input name="username" type="text" placeholder="Username"/>
                <input name="password" type="password" placeholder="Password"/>
                <button type="submit">Logg inn</button>
            </form>
        </div>
    )
}

export default Login;
