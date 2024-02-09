<template>
  <div class="grid grid-cols-3 gap-10">
    <div>
      <div class="stack w-full h-64" @click="totalView">
        <GalleryCard
          src="https://daisyui.com/images/stock/photo-1606107557195-0e29a4b5b4aa.jpg"
        >
        </GalleryCard>
        <GalleryCard
          src="https://daisyui.com/images/stock/photo-1606107557195-0e29a4b5b4aa.jpg"
        >
        </GalleryCard>
        <GalleryCard
          src="https://daisyui.com/images/stock/photo-1606107557195-0e29a4b5b4aa.jpg"
        >
        </GalleryCard>
      </div>
      <p class="mt-2 ml-4">전체보기</p>
    </div>
    <div>
      <div class="stack w-full h-64">
        <GalleryCard
          src="https://daisyui.com/images/stock/photo-1606107557195-0e29a4b5b4aa.jpg"
        >
        </GalleryCard>
        <GalleryCard
          src="https://daisyui.com/images/stock/photo-1606107557195-0e29a4b5b4aa.jpg"
        >
        </GalleryCard>
        <GalleryCard
          src="https://daisyui.com/images/stock/photo-1606107557195-0e29a4b5b4aa.jpg"
        >
        </GalleryCard>
      </div>
      <p class="mt-2 ml-4">추억영상함</p>
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
const emit = defineEmits(['totalView']);

const totalView = () => {
  emit('totalView');
};
</script>

<style lang="scss" scoped></style>
