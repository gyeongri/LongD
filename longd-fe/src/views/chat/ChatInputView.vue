<template>
  <div class="mt-4 flex items-center">
    <input
      type="text"
      v-model="message"
      @keyup.enter="sendMessageToMain"
      class="textarea textarea-bordered ml-2 mr-1"
    />
    <button
      @click="sendMessageToMain"
      class="img-btn rounded-full ml-3 hover:bg-pink-100"
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
input {
  border: px, solid, black;
  width: 100%;
}
.img-btn {
  background-image: url('/static/img/send.png');
  background-size: cover; /* 이미지가 버튼 크기에 맞게 조정됩니다 */
  background-position: center; /* 이미지가 중앙에 위치하도록 합니다 */
  width: 50px; /* 원하는 버튼의 너비 */
  height: 50px; /* 원하는 버튼의 높이 */
  border: none; /* 버튼의 기본 테두리 제거 */
}
</style>
