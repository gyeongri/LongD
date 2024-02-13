<template>
  <div
    class="h-[45rem] flex flex-col items-center justify-center border border-black"
  >
    <button @click="turnChat">
      <img
        src="/static/img/message-regular.svg"
        alt="asas"
        style="width: 30px; height: 30px; margin: 0 5px"
      />
    </button>
  </div>
</template>

<script setup>
const emit = defineEmits(['onChat']);
const turnChat = function () {
  emit('onChat');
};
</script>

<style scoped></style>
