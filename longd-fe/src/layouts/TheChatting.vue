<template>
  <!-- h-1/4, h-3/4 - 차지할 비율을 나타냄, w-1/4도 있음(가로버전) -->
  <div class="h-[30rem] flex flex-col">
    <ChatDisplayView
      @chatoff="TurnOffChat"
      :messages="messages"
      :count="count"
      :nickname="nickname"
      :lovername="lovername"
      class="overflow-scroll h-1/2 w-full"
    ></ChatDisplayView>
    <ChatInputView
      @messageToMain="sendMessage"
      class="w-full h-1/12"
    ></ChatInputView>
  </div>
</template>

<script setup>
import ChatInputView from '@/views/chat/ChatInputView.vue';
import ChatDisplayView from '@/views/chat/ChatDisplayView.vue';
import { onMounted, reactive, ref, onUnmounted } from 'vue';
import { stompApi } from '@/utils/api/index.js';
import { useUserStore } from '@/stores/user';
const userStore = useUserStore();
const { VITE_CHAT_BASE_IP } = import.meta.env;
const coupleId = ref('');
const messages = reactive([]);
const senderId = ref('');
const room = ref(null);
const count = ref(0);
const nickname = ref('');
const lovername = ref('');
const emit = defineEmits(['offChat']);

const TurnOffChat = function () {
  emit('offChat');
};
const sendMessage = message => {
  ws.value.send(
    '/app/chat/message',
    {},
    JSON.stringify({
      roomName: coupleId.value,
      senderId: senderId.value,
      content: message,
    }),
  );
  count.value++;
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

const connect = function (couple, sender) {
  ws.value.connect(
    {},
    frame => {
      coupleId.value = couple;
      senderId.value = sender;
      ws.value.subscribe(`/topic/chat/room/${couple}`, message => {
        const recv = JSON.parse(message.body);
        recvMessage(recv);
      });
      ws.value.send(
        '/app/chat/message',
        {},
        JSON.stringify({
          roomName: couple,
          senderId: sender,
        }),
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
  if (userStore.getUserState?.coupleListId !== undefined) {
    nickname.value = userStore.getUserState?.nickname;
    coupleId.value = userStore.getUserState?.coupleListId;
    stompApi
      .get('/user/findNickname', {
        params: {
          coupleListId: userStore.getUserState?.coupleListId,
          myNickname: userStore.getUserState?.nickname,
        },
      })
      .then(response => {
        lovername.value = response.data;
      })
      .catch(error => {
        console.error(error);
      });

    stompApi
      .get(`/chat/messages/${userStore.getUserState?.coupleListId}?size=30`)
      .then(res => {
        const sortedArray = res.data.sort((a, b) => a.id - b.id);
        sortedArray.forEach(element => {
          messages.push(element);
        });
      })
      .then(res => {
        senderId.value = userStore.getUserState?.id;
        connect(
          userStore.getUserState?.coupleListId,
          userStore.getUserState?.id,
        );
      })
      .catch(error => {
        console.error(error);
      });
  }
});

onUnmounted(() => {
  if (ws.value && ws.value.connectedState === 1) {
    ws.value.disconnect(() => {
      console.log('WebSocket disconnected on component unmount');
    });
  }
});
</script>

<style scoped></style>
