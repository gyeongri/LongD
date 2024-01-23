<script src="openvidu-browser-VERSION.js"></script>
<script src="app.js"></script>
<script setup>
import { OpenVidu } from 'openvidu-browser';
var OV;
var session;
var mySessionId = document.getElementById('sessionId').value;

OV = new OpenVidu();
session = OV.initSession();

session.on('streamCreated', function (event) {
  session.subscribe(event.stream, 'subscriber');
});

var APPLICATION_SERVER_URL = 'http://localhost:5000/';

function getToken(mySessionId) {
  return createSession(mySessionId).then(sessionId => createToken)
}

</script>

<template>

</template>

<style scoped>

</style>
