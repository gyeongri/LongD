<template>
  <div>
    <div class="chat-container" ref="chatContainer">
      <div
        v-for="(message, index) in messages"
        :key="message.id"
        :class="{
          chat: true,
          'chat-end': message.senderId === userId,
          'chat-start': message.senderId !== userId,
        }"
      >
        <div>
          <template v-if="shouldDisplayHeader(index)">
            <div class="chat-header">
              {{ message.senderId }}
            </div>
          </template>
          <div class="chat-bubble">{{ message.content }}</div>
          <time class="text-xs opacity-50">{{
            getFormattedTime(message.createdAt)
          }}</time>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { watch, ref, onMounted } from 'vue';
const chatContainer = ref();
const userId = ref('8');
const scrollToBottom = () => {
  chatContainer.value.scrollTop = chatContainer.value.scrollHeight;
};
const props = defineProps({
  messages: Array,
  count: Number,
});
const shouldDisplayHeader = index => {
  if (index === 0) {
    return true;
  }

  const currentSenderId = props.messages[index].senderId;
  const previousSenderId = props.messages[index - 1].senderId;
  const currentTime = new Date(props.messages[index].createdAt);
  const previousTime = new Date(props.messages[index - 1].createdAt);

  return (
    currentSenderId !== previousSenderId ||
    currentTime.getMinutes() !== previousTime.getMinutes()
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
</style>
