<script setup>
import axios from 'axios';
import { OpenVidu } from 'openvidu-browser';
import { ref } from 'vue';

axios.defaults.headers.post['Content-Type'] = 'application/json';
// const APPLICATION_SERVER_URL = process.env.NODE_ENV === 'production' ? '' : 'http://localhost:5000/';
var APPLICATION_SERVER_URL = 'http://localhost:5000/';


const OV = ref(undefined);
var session;
var mySessionId = document.getElementById('sessionId').value;

OV = new OpenVidu();
session = OV.initSession();

session.on('streamCreated', function (event) {
  session.subscribe(event.stream, 'subscriber');
});



function getToken(mySessionId) {
  return createSession(mySessionId).then(sessionId => createToken)
}

function createToken(sessionId) {
  return new Promise((resolve, reject) => {
    $.ajax({
      type: 'POST',
      url:
        APPLICATION_SERVER_URL + 'api/sessions/' + sessionId + '/connections',
      date: JSON.stringify({}),
      headers: { 'Content-Type': 'application/json' },
      success: response => resolve(response), // the token
      error: error => reject(error),
    });
  });
}
</script>

<template>

</template>

<style scoped>

</style>
