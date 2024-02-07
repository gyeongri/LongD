<template>
  <ul class="list-group">
    <li class="list-group-item" v-for="message in messages" :key="message.id">
      {{ message.senderId }} - {{ message.content }}
    </li>
  </ul>
</template>

<script setup>
const props = defineProps({
  messages: Array,
});
</script>

<style scoped></style>
