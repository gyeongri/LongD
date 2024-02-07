<template>
  <div v-if="true">
    <ChatDisplayView :messages="messages"> </ChatDisplayView>
    <ChatInputView @messageToMain="sendMessage"></ChatInputView>
  </div>
  <div v-else>
    <h1>열기</h1>
  </div>
</template>

<script setup>
import ChatInputView from '@/views/chat/ChatInputView.vue';
import ChatDisplayView from '@/views/chat/ChatDisplayView.vue';
import { onMounted, reactive, ref } from 'vue';
import { stompApi } from '@/utils/api/index.js';

const coupleId = ref(77);
const messages = reactive([]);
const sender = ref(8);
const room = ref(null);

const createRoom = async () => {
  const params = new URLSearchParams();
  params.append('roomName', coupleId.value);
  try {
    const response = await stompApi.post('/chat/room', params);
  } catch (error) {
    console.error(error);
  }
};

const findRoom = async () => {
  const response = await stompApi.get(`/chat/room/${coupleId.value}`);
  room.value = response.data;
};

const sendMessage = message => {
  console.log(message);
  ws.value.send(
    '/app/chat/message',
    {},
    JSON.stringify({
      roomName: coupleId.value,
      senderId: sender.value,
      content: message,
    }),
  );
};

const recvMessage = recv => {
  messages.push({ senderId: recv.senderId, content: recv.content });
};

// 웹소켓 연결 매서드
let reconnect = 0;
const sock = ref(new SockJS('http://localhost:8080/ws/chat'));
const ws = ref(Stomp.over(sock.value));

const connect = function () {
  ws.value.connect(
    {},
    frame => {
      ws.value.subscribe(`/topic/chat/room/${coupleId.value}`, message => {
        const recv = JSON.parse(message.body);
        recvMessage(recv);
      });
      ws.value.send(
        '/app/chat/message',
        {},
        JSON.stringify({ roomName: coupleId.value, senderId: sender.value }),
      );
    },
    error => {
      if (reconnect <= 5) {
        setTimeout(() => {
          console.log('connection reconnect');
          sock.value = new SockJS('http://localhost:8080/ws/chat');
          ws.value = Stomp.over(sock.value);
          connect();
        }, 10 * 1000);
      }
    },
  );
};

onMounted(() => {
  connect();
});
</script>

<style scoped></style>
