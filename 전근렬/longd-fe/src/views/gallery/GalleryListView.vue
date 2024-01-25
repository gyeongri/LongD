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

const router = useRouter();

// 임시 데이터
const items = ref([
  {
    id: 1,
    src: 'https://daisyui.com/images/stock/photo-1606107557195-0e29a4b5b4aa.jpg',
  },
  {
    id: 2,
    src: 'https://daisyui.com/images/stock/photo-1606107557195-0e29a4b5b4aa.jpg',
  },
  {
    id: 3,
    src: 'https://daisyui.com/images/stock/photo-1606107557195-0e29a4b5b4aa.jpg',
  },
  {
    id: 4,
    src: 'https://daisyui.com/images/stock/photo-1606107557195-0e29a4b5b4aa.jpg',
  },
  {
    id: 5,
    src: 'https://daisyui.com/images/stock/photo-1606107557195-0e29a4b5b4aa.jpg',
  },
  {
    id: 6,
    src: 'https://daisyui.com/images/stock/photo-1606107557195-0e29a4b5b4aa.jpg',
  },
  {
    id: 7,
    src: 'https://daisyui.com/images/stock/photo-1606107557195-0e29a4b5b4aa.jpg',
  },
  {
    id: 8,
    src: 'https://daisyui.com/images/stock/photo-1606107557195-0e29a4b5b4aa.jpg',
  },
  {
    id: 9,
    src: 'https://daisyui.com/images/stock/photo-1606107557195-0e29a4b5b4aa.jpg',
  },
  {
    id: 10,
    src: 'https://daisyui.com/images/stock/photo-1606107557195-0e29a4b5b4aa.jpg',
  },
  {
    id: 11,
    src: 'https://daisyui.com/images/stock/photo-1606107557195-0e29a4b5b4aa.jpg',
  },
  {
    id: 12,
    src: 'https://daisyui.com/images/stock/photo-1606107557195-0e29a4b5b4aa.jpg',
  },
  {
    id: 13,
    src: 'https://daisyui.com/images/stock/photo-1606107557195-0e29a4b5b4aa.jpg',
  },
  {
    id: 14,
    src: 'https://daisyui.com/images/stock/photo-1606107557195-0e29a4b5b4aa.jpg',
  },
  {
    id: 15,
    src: 'https://daisyui.com/images/stock/photo-1606107557195-0e29a4b5b4aa.jpg',
  },
  {
    id: 16,
    src: 'https://daisyui.com/images/stock/photo-1606107557195-0e29a4b5b4aa.jpg',
  },
  {
    id: 17,
    src: 'https://daisyui.com/images/stock/photo-1606107557195-0e29a4b5b4aa.jpg',
  },
  {
    id: 18,
    src: 'https://daisyui.com/images/stock/photo-1606107557195-0e29a4b5b4aa.jpg',
  },
  {
    id: 19,
    src: 'https://daisyui.com/images/stock/photo-1606107557195-0e29a4b5b4aa.jpg',
  },
]);

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
