<template>
  <div>
    <AppGrid :items="items">
      <template v-slot="{ item }">
        <GalleryCard :src="item.src" @click="goDetail(item.id)"></GalleryCard>
      </template>
    </AppGrid>
    <AppPagination />
  </div>
</template>

<script setup>
import GalleryCard from '@/components/gallery/GalleryCard.vue';
import AppPagination from '@/components/app/AppPagination.vue';
import AppGrid from '@/components/app/AppGrid.vue';
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { getAlbums } from '@/api/albums';
const router = useRouter();

const items = ref([]);

const fetchAlbums = () => {
  getAlbums().then(res => {
    // console.log(res.data);
    items.value = res.data;
  });
};

fetchAlbums();

const goDetail = id => {
  router.push({
    name: 'GalleryDetail',
    params: {
      id,
    },
  });
};
</script>

<style lang="scss" scoped></style>
