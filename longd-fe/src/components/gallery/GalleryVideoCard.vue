<template>
  <div
    class="card card-compact w-full h-64 bg-base-100 shadow-2xl transition-all ease-out duration-500 hover:shadow-2xl hover:transform hover:scale-105"
  >
    <video controls>
      <source
        :src="src"
        type="video/webm"
        class="object-cover overflow-hidden rounded-xl"
      />
    </video>
    <div class="card-body flex items-end mr-8">
      <h2 class="card-title">{{ title }}</h2>
      <div class="card-actions justify-end"></div>
    </div>
  </div>
</template>

<script setup>
defineProps({
  id: {
    type: Number,
  },
  src: {
    type: String,
  },
  title: {
    type: String,
  },
});
</script>

<style lang="scss" scoped>
/* 추가적인 스타일링을 위한 부분 */
</style>
