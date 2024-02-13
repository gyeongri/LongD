<template>
  <img
    :src="src"
    alt="#"
    class="object-cover w-full h-[42rem] overflow-hidden rounded-xl transition-shadow ease-out duration-500 hover:shadow-2xl"
  />
  <!-- h-auto도 가능 -->
</template>

<script setup>
defineProps({
  id: {
    type: Number,
  },
  src: {
    type: String,
  },
});
</script>

<style lang="scss" scoped></style>
