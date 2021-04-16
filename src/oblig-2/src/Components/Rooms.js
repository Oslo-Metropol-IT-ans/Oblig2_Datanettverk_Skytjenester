import React, {useState, useEffect} from 'react';
import {Link} from 'react-router-dom';
import '../App.css'

const Rooms = () => {

    const [rooms, setRooms] = useState([])

    useEffect(() => {
        if (sessionStorage.getItem("userId") === null) {
            window.location.replace("/")
        }else {
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

        console.log("effect");
        receive();
    }, [])

    const receive = () => {

        fetch("/api/rooms/getAll", {
            headers: {
                'Content-Type': 'application/json'
            },
            method: "GET"
            //body: JSON.stringify({userId: id})
        }).then((response) => {
            response.json().then(data => {
                console.log(data)
                setRooms(data)

            })
        })
    }

    const addRoom = () => {
        const room = {
            name: document.getElementById("name").value
        }

        fetch("/api/rooms/addOne", {
            headers: {
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify(room)
        }).then((response) => {
            response.json().then(data => {
                receive();
            })
        })
    }

    const logout = () => {
        sessionStorage.removeItem("userId");
    }

    return (
        <div className="Rooms">
            <div>
                <input type="text" id="name" name="name" placeholder="Room name"/>
                <button type="button" onClick={addRoom}>Register</button>
                <Link to='/'>
                    <button type="button" onClick={logout}>Log out</button>
                </Link>
            </div>
            <div>
                <h2>All rooms</h2>
                <ul>
                    {rooms.map(room => {
                    return <Link key={room.id} to={"/room/" + room.id} ><li>{room.name}</li></Link>
                    })}
                </ul>
            
            </div>

        </div>
    )
}

export default Rooms;
