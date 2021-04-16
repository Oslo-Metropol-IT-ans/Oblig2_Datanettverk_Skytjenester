import React, {useEffect} from 'react';
import {Link} from 'react-router-dom'

const Register = () => {

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
                register();
            }
        });


    }, [])
    const register = (props) =>{

        const user = {
            username : document.getElementById("username").value,
            password : document.getElementById("password").value
        }

        fetch("/api/users/addOne", {
            headers: {
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify(user)
        }).then((response) => {
            response.json().then(data => {
                console.log(data)
                sessionStorage.setItem("userId", data.id);
                window.location.replace("/rooms")
            })
        })
    }


    return(
        <div className="Register">
            <h1>Register new user</h1>
            <div>
                <input name="username" id="username" type="text" placeholder="username..."/>
                <input name="password" id="password" type="password" placeholder="password***"/>
            </div>
            
            <div>
                <Link to="/">
                <button type="button">Tilbake</button>
                </Link>
                <button type="button" onClick={register}>Registrer</button>
            </div>
            
        </div>
    )
}

export default Register;
