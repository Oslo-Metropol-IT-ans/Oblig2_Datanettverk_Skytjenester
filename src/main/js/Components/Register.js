import React from 'react';

const Register = () => {

    const register = (props) =>{

        const user = {
            username : document.getElementById("username").value,
            password : document.getElementById("password").value
        }

        fetch("http://localhost:8080/api/users/addOne", {
            headers: {
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify(user)
        }).then((response) => {
            response.json().then(data => {
                console.log(data)
                window.location.replace("/rooms/" + data.id)
            })
        })
    }

    return(
        <div className="Register">
            <h2>Register new user</h2>
            <input name="username" id="username" type="text" placeholder="username"></input>
            <input name="password" id="password" type="password" placeholder="password"></input>
            <button type="button" onClick={register}>Registrer</button>
        </div>
    )
}

export default Register;
