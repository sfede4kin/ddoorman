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

        const accountId = getAccountId();
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

const getProfile = () =>{
    const accountId = getAccountId();
    stompClient.send("/app/account." + accountId, {}, {});
}

const showProfile = (message) => {
    const keyTable = document.getElementById(keyElementId);
    var kg = message.keyGroups;
    kg.forEach(e => keyTable.insertAdjacentHTML('beforebegin', `<tr><td>${e.key.id}</td><td>${e.key.type}</td></tr>`));

    const doorTable = document.getElementById(doorElementId);
    var dg = message.doorGroups;
    dg.forEach(e => doorTable.insertAdjacentHTML('beforebegin', `<tr><td>${e.door.id}</td><td>${e.door.location}</td><td><button id="openDoor" class="btn btn-success" onclick="sendOpenDoorEvent(${e.door.id})">Open</button></td></tr>`));
}

const sendOpenDoorEvent = (doorId) => {
    var body = {"id": getUUID(), "refId": "", "accountId": getAccountId(), "keyId": "", "doorId": doorId, "ts": getTimestamp(), "type": "OPEN"};
    console.log(JSON.stringify(body));
    stompClient.send("/app/event." + accountId, {}, JSON.stringify(body));
}

/*
const clearData = () =>{
    $("#key-table tbody").remove();
}
*/

const getAccountId = () =>{
    return document.getElementById(accountIdElementId).value;
}

const getTimestamp = () =>{
    return new Date(Date.now()).toISOString();
}

const getUUID = () =>{
    var lut = []; for (var i=0; i<256; i++) { lut[i] = (i<16?'0':'')+(i).toString(16); }
    function e7()
    {
        var d0 = Math.random()*0xffffffff|0;
        var d1 = Math.random()*0xffffffff|0;
        var d2 = Math.random()*0xffffffff|0;
        var d3 = Math.random()*0xffffffff|0;
        return lut[d0&0xff]+lut[d0>>8&0xff]+lut[d0>>16&0xff]+lut[d0>>24&0xff]+'-'+
        lut[d1&0xff]+lut[d1>>8&0xff]+'-'+lut[d1>>16&0x0f|0x40]+lut[d1>>24&0xff]+'-'+
        lut[d2&0x3f|0x80]+lut[d2>>8&0xff]+'-'+lut[d2>>16&0xff]+lut[d2>>24&0xff]+
        lut[d3&0xff]+lut[d3>>8&0xff]+lut[d3>>16&0xff]+lut[d3>>24&0xff];
    }
    return e7();
}
