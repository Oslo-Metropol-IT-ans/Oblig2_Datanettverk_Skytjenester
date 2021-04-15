let stompClient = null;

const connect = () => {

    let socket = new SockJS('/note')
    stompClient = Stomp.over(socket);

    stompClient.connect({}, onConnect )

}

const onConnect = () => {
    stompClient.subscribe('/topic/public', onMessage);

    stompClient.send('/app/chat.send', {}, JSON.stringify({roomId: "1"}))
}

const onMessage = (payload) => {
    console.log(JSON.parse(payload.body).roomId);

    document.getElementById("messages").innerHTML = JSON.parse(payload.body).roomId;

}

const send = () => {
    let msg = document.getElementById("msg").value;
    stompClient.send('/app/chat.send', {}, JSON.stringify({roomId: msg}));
}
