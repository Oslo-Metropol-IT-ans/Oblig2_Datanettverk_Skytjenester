import React, {useState, useEffect} from 'react';
import { useParams } from 'react-router-dom'
import '../App.css';
import {Link} from 'react-router-dom';

import SockJS from 'sockjs-client';
import Stomp from 'stompjs';

const Room = (props) => {

    const [messages, setMessages] = useState([]);

    const [register, setRegister] = useState(false);

    const [room, setRoom] = useState("");

    let {id} = useParams();

    const getName = () => {
        fetch(`/api/room/${id}`,
        ).then(response => response.json().then(data => {
            //console.log(data);
            setRoom(data.name);
            console.log(room);
        }))
    }

    let socket = new SockJS('/note')
    let stompClient = Stomp.over(socket);

    const connect = () => {

        stompClient.connect({}, onConnect)

    }

    const onConnect = () => {
        stompClient.subscribe('/topic/public', onMessage);

        //stomp.send('/app/chat.send', {}, JSON.stringify({roomId: "1"}))
    }

    const onMessage = (payload) => {
        let room = JSON.parse(payload.body).roomId;
        if (room === id) {
            update();
        }
        console.log(JSON.parse(payload.body).roomId);
    }

    useEffect(() => {
        getName();
        //console.log(sessionStorage.getItem("userId"))
        if (sessionStorage.getItem("userId") == null) {
            window.location.replace("/")
        } else {
            fetch(`/api/user/${sessionStorage.getItem("userId")}`,{
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(response => {
                if(response.status !== 200) {
                    sessionStorage.removeItem("userId");
                    window.location.replace("/")
            }
            })
        }
        validate();
        update();
        document.getElementById("message");

        document.getElementById("message").addEventListener('keypress', (e) => {
            if (e.key === 'Enter') {
                send();
            }
        })

        connect();
    }, [])

    const validate = () => {

        fetch(`/api/room/${id}/users`).then((response) =>{
            response.json().then(data => {
                const userId = sessionStorage.getItem("userId");
                //console.log(data)
                for (let user of data) {
                    if(user.id == userId) {
                        setRegister(true);
                        document.getElementById("message").focus();
                    }
                } 
            })
        })
    }

    const addToRoom = () => {
        const user = {
            userId: sessionStorage.getItem("userId")
        }

        fetch(`/api/room/${id}/users`,{
            headers: {
                'Content-Type': 'application/json'
            },method: "POST",
            body: JSON.stringify(user)
        }
        ).then(response => validate())
    }

    const update =  ()  => {
        fetch(`/api/message/fire/${id}`,{
        headers: {
            'Content-Type': 'application/json'
        },
        method: "GET"})
        .then(response => response.json().then(data => {
            //console.log(data)
            setMessages(data)
            let element = document.getElementById("box");
            element.scrollTop = element.scrollHeight;
        }))
    }

    const send = () => {
        const message = {
            room_id: id,
            user_id: sessionStorage.getItem("userId"),
            message: document.getElementById("message").value
        };

        stompClient.send('/app/chat.send', {}, JSON.stringify({roomId: id}));

        fetch(`/api/message`,{
        headers: {
            'Content-Type': 'application/json'
        },
        method: "POST",
        body: JSON.stringify(message)
        })
        .then(response => response.json().then(data => {
            document.getElementById("message").value = "";
            document.getElementById("message").focus();
        }))
    }

    return(
        <div className="Room">
            <div className={`${register ? "show": "hide"}`}>
                <h1>{room}</h1>
                <div className="MessageBox" id="box">
                    {messages.map((message) => {
                        return <div key={message.id} className={`${message.user_id == sessionStorage.
                        getItem("userId") ? "right" : "left"}`}>
                            <p className={`${message.user_id == sessionStorage.
                            getItem("userId") ? "rightUsername" : "leftUsername"}`}>{message.username}</p><br/>
                            <p key={message.id} className={`${message.user_id == sessionStorage.
                        getItem("userId") ? "green" : "white"}`}> {message.message}</p>
                        </div>
                    })}
                </div>
                <div>
                    <Link to="/rooms">
                        <button>Tilbake</button>
                    </Link>
                    <input name="text" id="message" type="text" placeholder="message"/>
                    <button type="button" onClick={send}>👆</button>
                </div>
            </div>
            <div className={`${register ? "hide": "show"}`}>
                <h2>Register to {room}</h2>
                <Link to="/rooms">
                    <button>Tilbake</button>
                </Link>
                <button onClick={addToRoom} type="button">Register</button>
            </div>
            
            
        </div>
    )
}

export default Room;
