<template>
  <div class="h-[864px]">
    <button @click="turnChat">열기</button>
  </div>
</template>

<script setup>
const emit = defineEmits(['onChat']);
const turnChat = function () {
  emit('onChat');
};
</script>

<style scoped></style>
