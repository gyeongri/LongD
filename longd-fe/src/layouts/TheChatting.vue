<template>
  <!-- h-1/4, h-3/4 - 차지할 비율을 나타냄, w-1/4도 있음(가로버전) -->
  <div class="h-[45rem] flex flex-col">
    <ChatDisplayView
      :messages="messages"
      class="border-4 border-blue-500 h-3/4"
    ></ChatDisplayView>
    <ChatInputView
      @messageToMain="sendMessage"
      class="border-4 border-blue-500 h-1/4"
    >
    </ChatInputView>
  </div>
</template>

<script setup>
import ChatInputView from '@/views/chat/ChatInputView.vue';
import ChatDisplayView from '@/views/chat/ChatDisplayView.vue';
import { onMounted, reactive, ref } from 'vue';
import { stompApi } from '@/utils/api/index.js';
import { useUserStore } from '@/stores/user';
const userStore = useUserStore();
const { VITE_CHAT_BASE_IP } = import.meta.env;

const coupleId = ref('');
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
  sender.value = userStore.getUserState?.nickname;
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
  messages.push({
    senderId: recv.senderId,
    content: recv.content,
    createdAt: recv.createdAt,
  });
};

// 웹소켓 연결 매서드
let reconnect = 0;
const sock = ref(new SockJS(`${VITE_CHAT_BASE_IP}/ws/chat`));
const ws = ref(Stomp.over(sock.value));

const connect = function () {
  coupleId.value = userStore.getUserState?.coupleListId;
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
          sock.value = new SockJS(`${VITE_CHAT_BASE_IP}/ws/chat`);
          ws.value = Stomp.over(sock.value);
          connect();
        }, 10 * 1000);
      }
    },
  );
};

onMounted(() => {
  stompApi
    .get(`/chat/messages/${coupleId.value}?size=30`)
    .then(res => {
      const sortedArray = res.data.sort((a, b) => a.id - b.id);
      sortedArray.forEach(element => {
        messages.push(element);
      });
    })
    .catch(error => {
      console.error(error);
    });
  connect();
});
</script>

<style lang="scss" scoped></style>
