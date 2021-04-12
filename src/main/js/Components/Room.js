import React, {useState, useEffect} from 'react';
import { useParams } from 'react-router-dom'
import '../App.css';

const Room = (props) => {

    const [messages, setMessages] = useState([]);

    let {id, userId} = useParams();

    useEffect(() => {
        console.log("effect");
        update();
    }, [])

    const update = async ()  => {
        fetch(`http://localhost:8080/api/message/en/${id}`,{
        headers: {
            'Content-Type': 'application/json'
        },
        method: "GET"})
        .then(response => response.json().then(data => {
            console.log(data)
            setMessages(data)
        }))
    }

    const send = () => {
        const message = {
            room_id: id,
            user_id: userId,
            message: document.getElementById("message").value
        }

        fetch(`http://localhost:8080/api/message`,{
        headers: {
            'Content-Type': 'application/json'
        },
        method: "POST",
        body: JSON.stringify(message)
        })
        .then(response => response.json().then(data => {
            update();
        }))
    }

    return(
        <div className="Room">
            <h1>Messages</h1>
            <div className="MessageBox">
                {messages.map((message) => {
                    return <p key={message.id} className={`${message.user_id == userId ? "green" : "white"}`}>{message.message}</p>
                })}
            </div>
            <div>
                <input name="text" id="message" type="text" placeholder="message"></input>
                <button type="button" onClick={send}>-</button>
            </div>
            
        </div>
    )
}

export default Room;
