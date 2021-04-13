import React from 'react';

const Register = () => {

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
                localStorage.setItem("id",data.id)
                window.location.replace("/rooms/")
            })
        })
    }

    return(
        <div className="Register">
            <h2>Register new user</h2>
            <input name="username" id="username" type="text" placeholder="username"/>
            <input name="password" id="password" type="password" placeholder="password"/>
            <button type="button" onClick={register}>Register</button>
        </div>
    )
}

export default Register;
