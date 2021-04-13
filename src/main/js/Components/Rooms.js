import React, {useState, useEffect} from 'react';
import {useParams, Link} from 'react-router-dom';
import Room from './Room'
import '../App.css'

const Rooms = () => {

    const [rooms, setRooms] = useState([])

    let {id} = useParams();

    useEffect(() => {
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

    return (
        <div className="Rooms">
            <div>
                <input type="text" id="name" name="name" placeholder="Room name"/>
                <button type="button" onClick={addRoom}>Register</button>
            </div>
            <div>
            {rooms.map(room => {
                return <Link key={room.id} to={"/room/" + room.id + "/" + id} >{room.name}</Link>
            })}
            </div>

        </div>
    )
}

export default Rooms;
