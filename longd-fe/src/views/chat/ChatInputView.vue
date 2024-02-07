<template>
  <div>
    <input
      type="text"
      v-model="message"
      @keyup.enter="sendMessageToMain"
      class="border-4 border-blue-300 h-3/4"
    />
    <div class="border-4 border-blue-300 h-1/4 flex justify-end">
      <button @click="sendMessageToMain" class="btn btn-outline btn-primary">
        전송
      </button>
    </div>
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
