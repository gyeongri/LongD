<template>
  <div class="bg-slate-50 rounded-lg shadow-md pb-5 border-b-2">
    <div class="pl-3">
      <button @click="turnOff">>>>></button>
    </div>

    <div class="chat-container p-1" ref="chatContainer">
      <div
        v-for="(message, index) in messages"
        :key="message.id"
        :class="{
          chat: true,
          'chat-end': message.senderId == userId,
          'chat-start': message.senderId != userId,
        }"
        class="flex flex-col"
      >
        <!-- 사용자 ID와 프로필 이미지를 메시지 스레드의 첫 부분에만 표시 -->
        <template v-if="shouldDisplayHeader(index)">
          <div class="flex flex-col">
            <!-- 사용자('나')의 메시지일 경우 -->
            <template v-if="message.senderId == userId">
              <div
                class="flex items-center justify-end"
                style="margin-right: 1rem; margin-bottom: 0.5rem"
              >
                <span class="text-sm text-stone-500">{{ nickname }}</span>
                <img
                  :src="userProfileImage"
                  class="w-8 h-8 rounded-full ml-3"
                />
              </div>
            </template>
            <!-- 상대방의 메시지일 경우 -->
            <template v-else>
              <div class="flex items-center">
                <img
                  :src="otherUserProfileImage"
                  class="w-8 h-8 rounded-full mr-3"
                  style="margin-left: 1rem; margin-bottom: 0.5rem"
                />
                <span class="text-sm text-stone-500">{{ lovername }}</span>
              </div>
            </template>
          </div>
        </template>
        <!-- 메시지 버블과 시간을 표시하는 부분 -->
        <div
          :class="{
            flex: true,
            'gap-2': true,
            'justify-end': message.senderId == userId,
            'justify-start': message.senderId != userId,
          }"
        >
          <!-- 사용자('나')의 메시지일 경우 시간을 왼쪽에 표시 -->
          <template v-if="message.senderId == userId">
            <div class="flex gap-2">
              <div class="flex items-end">
                <time class="text-xs opacity-50 gap-2 pt-4/5">{{
                  getFormattedTime(message.createdAt)
                }}</time>
              </div>
              <div class="chat-bubble p-1 rounded-lg">
                <p class="text-sm text-stone-500">{{ message.content }}</p>
              </div>
            </div>
          </template>
          <!-- 상대방의 메시지일 경우 시간을 오른쪽에 표시 -->
          <template v-else>
            <div class="flex gap-2">
              <div class="chat-bubble p-1 rounded-lg">
                <p class="text-sm text-stone-500">{{ message.content }}</p>
              </div>
              <div class="flex items-end">
                <time class="text-xs opacity-50 gap-2 pt-4/5">{{
                  getFormattedTime(message.createdAt)
                }}</time>
              </div>
            </div>
          </template>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user';
import { watch, ref, onMounted } from 'vue';
import { partnerinfo } from '@/utils/api/user';
const userStore = useUserStore();
const emit = defineEmits(['chatoff']);
const turnOff = function () {
  emit('chatoff');
};
const userProfileImage = ref(
  'https://i.namu.wiki/i/ijg40CIiHx5-Ihr3ksIJUm4cQQDEnek8xMEmJaQqGR5U13DKOZnCkzwPx1L5rcEX2-xxFYAyQO7XTcyqQ2BGEw.webp',
);
const otherUserProfileImage = ref(
  'https://sitem.ssgcdn.com/64/38/07/item/1000414073864_i1_750.jpg',
);
const partnerInfo = ref('');
const chatContainer = ref();
const userId = ref(userStore.getUserState.id);
const scrollToBottom = () => {
  chatContainer.value.scrollTop = chatContainer.value.scrollHeight;
};
const props = defineProps({
  messages: Array,
  count: Number,
  nickname: String,
  lovername: String,
});
const shouldDisplayHeader = index => {
  if (index === 0) {
    return true;
  }
  const nickname = ref(props.nickname);
  const lovername = ref(props.lovername);
  const currentSenderId = props.messages[index].senderId;
  const previousSenderId = props.messages[index - 1].senderId;
  // const currentTime = new Date(props.messages[index].createdAt);
  // const previousTime = new Date(props.messages[index - 1].createdAt);
  return (
    currentSenderId !== previousSenderId
    // currentTime.getMinutes() !== previousTime.getMinutes()
  );
};
watch(
  () => props.count,
  (newvalue, oldvalue) => {
    setTimeout(() => scrollToBottom(), 100);
  },
);
const getFormattedTime = createdAt => {
  const date = new Date(createdAt);
  const hours = date.getHours();
  const minutes = date.getMinutes();
  return `${hours}:${minutes}`;
};
onMounted(() => {
  setTimeout(() => scrollToBottom(), 100);
  partnerinfo(
    data => {
      partnerInfo.value = data.data;
      otherUserProfileImage.value = partnerInfo.value?.profilePicture;
      userProfileImage.value = userStore.getUserState?.profilePicture;
    },
    error => {
      console.log('Partner Info 가져오기 안됨', error);
    },
  );
});
</script>

<style scoped>
.chat-container {
  max-height: 100%;
  overflow-y: auto; /* 스크롤이 필요한 경우만 스크롤 나타남 */
  scrollbar-width: none; /* for Firefox */
}
div::-webkit-scrollbar {
  display: none; /* for Chrome, Safari, and Opera */
}
.chat-message {
  display: flex;
  align-items: center;
}
.chat-end .chat-bubble {
  background-color: #e2f1ff; /* 연한 파란색으로 변경 (예시) */
  margin-right: 1vw; /* 오른쪽 마진 설정 */
}
.chat-end time {
  order: -1; /* 시간을 버블 왼쪽에 배치 */
}
.chat-start .chat-bubble {
  background-color: #ffeded; /* 연한 분홍색으로 유지 */

  margin-left: 1rem; /* 왼쪽 마진 설정 */
}
p {
  font-size: 16px;
}
.chat-bubble {
  padding: 0.5rem;
}
</style>
