<template>
  <div class="card card-compact w-96 bg-base-100 shadow-xl relative" style="height: 300px">
    <figure class="w-full h-full relative">
      <img :src="src" alt="#" class="object-contain w-full h-full" />
      <div class="absolute top-0 right-0 m-4 bg-primary text-white px-3 py-1 rounded">
        <input type="checkbox" checked="checked" class="checkbox checkbox-xs" />
      </div>
    </figure>
    <div class="card-body">
      <h2 class="card-title">{{ id }}</h2>
    </div>
  </div>
</template>

<script setup>
defineProps({
  id: {
    type: Number
  },
  src: {
    type: String
  }
})
</script>

<style lang="scss" scoped>
/* 추가적인 스타일링을 위한 부분 */
</style>
