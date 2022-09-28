let stompClient = null;

const doorIdElementId = "doorId";
const eventElementId = "event";

const setConnected = (connected) => {
    const connectBtn = document.getElementById("connect");
    const disconnectBtn = document.getElementById("disconnect");

    connectBtn.disabled = connected;
    disconnectBtn.disabled = !connected;
}

const connect = () => {
    const doorId = getDoorId();
    var headers = {"doorId": doorId};
    stompClient = Stomp.over(new SockJS('/gs-guide-websocket'));
    stompClient.connect(headers, (frame) => {
        setConnected(true);
        console.log(`Connected as door id: ${doorId} frame:${frame}`);
        stompClient.subscribe('/queue/event.' + doorId, (message) => {showEvent(message.body); sendDoorOpenedEvent(message.body)}, headers);
    });
}

const disconnect = () => {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

const showEvent = (message) => {
    const eventLine = document.getElementById(eventElementId);
    let newRow = eventLine.insertRow(-1);
    let newCell = newRow.insertCell(0);
    let newText = document.createTextNode(message);
    newCell.appendChild(newText);
}

const sendDoorOpenedEvent = (message) => {
    var msgObj = JSON.parse(message);
    var body = {"sourceId": getUUID(), "refId": msgObj.sourceId, "accountId": msgObj.accountId, "keyId": msgObj.keyId, "doorId": msgObj.doorId, "ts": getTimestamp(), "type": "OPENED"};
    var bodyJson = JSON.stringify(body);
    console.log(bodyJson);
    stompClient.send("/door/response.event." + msgObj.doorId, {}, bodyJson);
    showEvent(bodyJson);
}

const getDoorId = () =>{
    return document.getElementById(doorIdElementId).value;
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
