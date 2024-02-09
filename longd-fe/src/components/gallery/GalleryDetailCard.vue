<template>
<<<<<<< HEAD:전근렬/longd-fe/src/components/gallery/GalleryDetailCard.vue
  <div class="card card-compact w-960 bg-base-100 shadow-xl">
    <figure>
      <img :src="src" alt="#" />
=======
<<<<<<<< HEAD:전근렬/longd-fe/src/components/gallery/GalleryCard.vue
  <div
    class="card card-compact w-96 bg-base-100 shadow-xl relative"
    style="height: 300px"
  >
    <figure class="w-full h-full relative">
      <img :src="src" alt="#" class="object-contain w-full h-full" />
      <div
        class="absolute top-0 right-0 m-4 bg-primary text-white px-3 py-1 rounded"
      >
        <input type="checkbox" checked="checked" class="checkbox checkbox-xs" />
      </div>
========
  <div class="card card-compact w-960 bg-base-100 shadow-xl">
    <figure>
      <img :src="src" alt="#" />
>>>>>>>> f1d41673cfc69d2c419b4645f6dfdd209d7f64bf:longd-fe/src/components/gallery/GalleryDetailCard.vue
>>>>>>> f1d41673cfc69d2c419b4645f6dfdd209d7f64bf:longd-fe/src/components/gallery/GalleryDetailCard.vue
    </figure>
    <div class="card-body">
      <h2 class="card-title">{{ id }}</h2>
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
});
</script>

<<<<<<< HEAD:전근렬/longd-fe/src/components/gallery/GalleryDetailCard.vue
<style lang="scss" scoped></style>
=======
<style lang="scss" scoped>
/* 추가적인 스타일링을 위한 부분 */
</style>
>>>>>>> f1d41673cfc69d2c419b4645f6dfdd209d7f64bf:longd-fe/src/components/gallery/GalleryDetailCard.vue
