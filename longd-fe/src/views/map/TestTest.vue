<template>
<<<<<<< HEAD:전근렬/longd-fe/src/views/gallery/GalleryDetailView.vue
  <GalleryDetailCard :id="item.id" :src="item.src" />
=======
  <div></div>
>>>>>>> f1d41673cfc69d2c419b4645f6dfdd209d7f64bf:longd-fe/src/views/map/TestTest.vue
</template>

<script setup>
import GalleryDetailCard from '@/components/gallery/GalleryDetailCard.vue';
import { getAlbumById } from '@/api/albums';
import { useRoute } from 'vue-router';
import { ref } from 'vue';

// route의 파라미터에서 id 추출
const route = useRoute();
const id = route.params.id;

const item = ref([]);
const fetchAlbum = () => {
  getAlbumById(id)
    .then(res => {
      item.value = res.data;
    })
    .catch(err => {
      console.log('err', err);
    });
};

fetchAlbum();
</script>

<style lang="scss" scoped></style>
