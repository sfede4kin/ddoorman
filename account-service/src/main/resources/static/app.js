let stompClient = null;

const keyElementId = "key";
const doorElementId = "door";
const accountIdElementId = "accountId";
const messageElementId = "message";


const setConnected = (connected) => {
    const connectBtn = document.getElementById("connect");
    const disconnectBtn = document.getElementById("disconnect");

    connectBtn.disabled = connected;
    disconnectBtn.disabled = !connected;
    //const chatLine = document.getElementById(chatLineElementId);
    //chatLine.hidden = !connected;
}

const connect = () => {
    stompClient = Stomp.over(new SockJS('/gs-guide-websocket'));
    stompClient.connect({}, (frame) => {
        setConnected(true);
        getProfile();

        const accountId = document.getElementById(accountIdElementId).value;
        console.log(`Connected as account id: ${accountId} frame:${frame}`);
        stompClient.subscribe('/topic/profile.' + accountId, (message) => showProfile(JSON.parse(message.body)));
    });
}

const disconnect = () => {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

/*const sendMsg = () => {
    const roomId = document.getElementById(roomIdElementId).value;
    const message = document.getElementById(messageElementId).value;
    stompClient.send("/app/message." + roomId, {}, JSON.stringify({'messageStr': message}))
}*/

const getProfile = () =>{
    const accountId = document.getElementById(accountIdElementId).value;
    stompClient.send("/app/account." + accountId, {}, {});
}

const showProfile = (message) => {
    const keyTable = document.getElementById(keyElementId);
    var kg = message.keyGroups;
    kg.forEach(elem => keyTable.insertAdjacentHTML('beforebegin', `<tr><td>${elem.key.id}</td><td>${elem.key.type}</td></tr>`));

    const doorTable = document.getElementById(doorElementId);
    var dg = message.doorGroups;
    dg.forEach(elem => doorTable.insertAdjacentHTML('beforebegin', `<tr><td>${elem.door.id}</td><td>${elem.door.location}</td></tr>`));
}

const clearData = () =>{
    $("#key-table tbody").remove();
}
