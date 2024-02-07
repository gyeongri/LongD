<template>
  <div>
    <input type="text" v-model="message" @keyup.enter="sendMessageToMain" />
    <br />
    <button @click="sendMessageToMain">전송</button><br />
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
}
</style>
