<template>
  <div
    class="h-[30rem] flex flex-col items-center justify-center cursor-pointer shadow-md fixed w-[3rem] bg-slate-50 rounded-lg"
    @click="turnChat"
  >
    <img
      src="/static/img/noMsg.png"
      alt="asas"
      style="width: 30px; height: 30px; margin: 0 5px border-radius:5px"
    />
  </div>
</template>

<script setup>
const emit = defineEmits(['onChat']);
const turnChat = function () {
  emit('onChat');
};
</script>

<style scoped></style>
