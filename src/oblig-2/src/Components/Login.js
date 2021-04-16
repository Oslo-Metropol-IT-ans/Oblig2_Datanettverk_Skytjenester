import React, {useEffect} from 'react';
import '../App.css';
import {Link} from 'react-router-dom'

const Login = () => {

    useEffect(() => {
        let userName = document.getElementById("username");
        let passWord = document.getElementById("password");
        userName.focus();
        userName.addEventListener("keypress", (e) => {
            if (e.key === "Enter"){
                passWord.focus();
            }
        });
        passWord.addEventListener("keypress", (e) => {
            if (e.key === "Enter"){
                login();
            }
        });


    }, [])

    const login = () => {
        const user = {
            username: document.getElementById("username").value,
            password: document.getElementById("password").value
        };

        fetch("/api/users/getByUser", {
            headers: {
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify(user)
        }).then((response) => {
            response.json().then(data => {
                console.log(data)
                sessionStorage.setItem("userId", data.id);
                window.location.replace("/rooms/")
            })
        })
    }

    return (
        <div className="Login">
            <h1>Log in</h1>
            <div>
                <input id="username" name="username" type="text" placeholder="username..."/>
                <input id="password" name="password" type="password" placeholder="password***"/>
            </div>
            <div>
                <Link to="/">
                    <button>Tilbake</button>
                </Link>
                <button type="button" onClick={login}>Logg inn</button>
            </div>
                
        </div>
    )
}

export default Login;
