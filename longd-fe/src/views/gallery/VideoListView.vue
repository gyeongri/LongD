<template>
  <AppGrid class="mb-10" :items="videoList">
    <template v-slot="{ item }">
      <GalleryVideoCard
        :src="item.pathUrl"
        :id="item.id"
        :title="item.title"
      ></GalleryVideoCard>
    </template>
  </AppGrid>

  <!-- <div>
    <div v-for="item in videoList" :key="item.id">
      {{ item }}
      <video width="320" height="240" controls autoplay>
        <source :src="item.pathUrl" type="video/webm" />
      </video>
    </div>
  </div> -->
</template>

<script setup>
import GalleryVideoCard from '@/components/gallery/GalleryVideoCard.vue';
import AppGrid from '@/components/app/AppGrid.vue';
import { getVideo } from '@/utils/api/albums';
import { onMounted, ref } from 'vue';
const videoList = ref([]);
// 조회 관련 파라미터(전체)
const params = ref({
  _limit: 6, // 몇개씩 조회
  _page: 1, // 현재 페이지를 조회
  _sort: 'id', // 무엇을
  _order: 'desc', // 내림차순
  // id_like: '', // 해당 요소 검색 기능
});
onMounted(() => {
  getVideo(params.value)
    .then(res => {
      videoList.value = res.data;
      console.log(res.data);
    })
    .catch(error => {
      console.error(error);
    });
});
</script>

<style scoped></style>
