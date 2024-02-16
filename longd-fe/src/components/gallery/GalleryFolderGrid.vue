<template>
  <div class="grid grid-cols-3 gap-10">
    <div>
      <div class="stack w-full h-64" @click="totalView">
        <GalleryCard
          src="https://i.pinimg.com/564x/b4/bd/75/b4bd756f0d38cf1589b6a9ebc8b5fc32.jpg"
        >
        </GalleryCard>
        <GalleryCard
          src="https://i.pinimg.com/564x/b4/bd/75/b4bd756f0d38cf1589b6a9ebc8b5fc32.jpg"
        >
        </GalleryCard>
        <GalleryCard
          src="https://i.pinimg.com/564x/b4/bd/75/b4bd756f0d38cf1589b6a9ebc8b5fc32.jpg"
        >
        </GalleryCard>
      </div>
      <p class="mt-2 ml-4 font-bold">전체보기</p>
    </div>
    <div>
      <div class="stack w-full h-64" @click="totalVideo">
        <GalleryCard
          src="https://i.pinimg.com/564x/b4/bd/75/b4bd756f0d38cf1589b6a9ebc8b5fc32.jpg"
        >
        </GalleryCard>
        <GalleryCard
          src="https://i.pinimg.com/564x/b4/bd/75/b4bd756f0d38cf1589b6a9ebc8b5fc32.jpg"
        >
        </GalleryCard>
        <GalleryCard
          src="https://i.pinimg.com/564x/b4/bd/75/b4bd756f0d38cf1589b6a9ebc8b5fc32.jpg"
        >
        </GalleryCard>
      </div>
      <p class="mt-2 ml-4 font-bold">추억영상함</p>
    </div>
    <div v-for="(item, index) in items" :key="index" :class="colClass">
      <slot :item="item" :index="index"></slot>
    </div>
  </div>
</template>

<script setup>
import GalleryCard from '@/components/gallery/GalleryCard.vue';
defineProps({
  items: {
    type: Array,
    required: true,
  },
  colClass: {
    type: String,
    default: 'col-span-1',
  },
});
const emit = defineEmits(['totalView', 'totalVideo']);

const totalView = () => {
  emit('totalView');
};
const totalVideo = () => {
  emit('totalVideo');
};
</script>

<style lang="scss" scoped></style>
