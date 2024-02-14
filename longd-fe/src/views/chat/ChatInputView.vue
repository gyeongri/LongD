<template>
  <div class="flex items-center relative">
    <textarea
      rows="3"
      v-model="message"
      @keyup.enter="sendMessageToMain"
      class="textarea textarea-bordered w-full resize-none pr-12"
    ></textarea>
    <button
      @click="sendMessageToMain"
      class="img-btn rounded-full absolute right-3 bottom-3"
    ></button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
const emit = defineEmits(['messageToMain']);
const message = ref('');

const sendMessageToMain = function () {
  emit('messageToMain', message.value);
  console.log(message.value);
  message.value = ''; // 초기화
  const inputField = document.querySelector('input[type="text"]');
  if (inputField) {
    inputField.value = '';
  }
};
</script>

<style scoped>
textarea {
  border: 1px, solid, black;
  width: 100%;
}

.img-btn {
  background-image: url('/static/img/send.png');
  background-size: contain; /* 이미지가 버튼 크기에 맞게 조정됩니다 */
  background-position: center; /* 이미지가 중앙에 위치하도록 합니다 */
  background-repeat: no-repeat;
  position: absolute; /* 절대 위치 지정 */
  right: 0.5rem; /* 오른쪽 padding 공간에 맞춰 버튼을 위치 */
  bottom: 2.5rem; /* 아래쪽 padding 공간에 맞춰 버튼을 위치 */
  width: 2.5rem; /* 버튼의 너비 */
  height: 2.5rem; /* 버튼의 높이 */
  border: none; /* 버튼의 기본 테두리 제거 */
}
</style>
