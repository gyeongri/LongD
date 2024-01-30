<!-- 모달창으로 구현 예정 -->
<!-- 구현하고 삭제 예정 -->
<template>
  <div>
    <div class="mb-4">
      <GalleryCard v-if="src" :src="src" />
      <GalleryCard v-else :src="defaultImage" />
    </div>
    <input
      type="file"
      class="file-input file-input-bordered file-input-primary w-full max-w-xs"
      @change="previewImage"
    />
    <button @click="save" class="btn btn-outline btn-primary">저장</button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import GalleryCard from '@/components/gallery/GalleryCard.vue';
import { createAlbum } from '@/api/albums';
import { useRouter } from 'vue-router';

const router = useRouter();

const src = ref('');

const defaultImage = '/src/assets/images/icon_upload.png'; // 디폴트 이미지 경로

const previewImage = event => {
  const file = event.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = () => {
      src.value = reader.result;
    };
    reader.readAsDataURL(file);
  } else {
    src.value = '';
  }
};

const save = async () => {
  try {
    console.log(src);
    await createAlbum({
      src: 'https://daisyui.com/images/stock/photo-1606107557195-0e29a4b5b4aa.jpg',
      createdAt: Date.now(),
    });
    router.push({ name: 'GalleryList' });
  } catch (err) {
    console.log(err);
  }
};
</script>

<style lang="scss" scoped></style>
