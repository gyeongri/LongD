<template>
  <div>
    <h1>about 페이지</h1>
    <div class="stack">
      <GalleryCard
        v-if="items.length > 0"
        :src="items[0].src"
        :id="items[0].id"
      ></GalleryCard>
      <GalleryCard
        v-if="items.length > 1"
        :src="items[1].src"
        :id="items[1].id"
      ></GalleryCard>
      <GalleryCard
        v-if="items.length > 2"
        :src="items[2].src"
        :id="items[2].id"
      ></GalleryCard>
    </div>
    <button @click="showAlert" class="btn btn-outline btn-primary">
      Hello world
    </button>
  </div>
</template>

<script setup>
import { getAlbums } from '@/utils/api/albums';
import { ref, watchEffect } from 'vue';
import GalleryCard from '@/components/gallery/GalleryCard.vue';
import Swal from 'sweetalert2';
const items = ref([]);

const params = ref({
  _sort: 'createdAt', // 무엇을
  _order: 'desc', // 내림차순
});

const showAlert = () => {
  Swal.fire('Hello Vue world!!!');
};

const fetchAlbums = async () => {
  try {
    const { data } = await getAlbums(params.value);
    items.value = data;
  } catch (err) {
    console.error(err);
  }
};

watchEffect(fetchAlbums);
</script>
