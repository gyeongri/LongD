<template>
  <GalleryDetailCard :id="item.id" :src="item.src" />
</template>

<script setup>
import GalleryDetailCard from '@/components/gallery/GalleryDetailCard.vue';
import { getAlbumById } from '@/utils/api/albums';
import { useRoute } from 'vue-router';
import { ref } from 'vue';

// route의 파라미터에서 id 추출
const route = useRoute();
const id = route.params.id;
// const folderName = route.params.folderName;

// 현재 item 조회
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
