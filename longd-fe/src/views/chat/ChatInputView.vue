<template>
  <div class="mt-4 flex items-center">
    <input
      type="text"
      v-model="message"
      @keyup.enter="sendMessageToMain"
      class="flex-1 py-2 px-3 rounded-full bg-gray-100 focus:outline-none"
    />
    <button
      class="bg-blue-500 text-white px-4 py-2 rounded-full ml-3 hover:bg-blue-600"
    >
      전송
    </button>
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
</style>
