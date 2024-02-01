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
    <button @click="showAlert2" class="btn btn-outline btn-primary">
      Hello world
    </button>
    <button @click="showCombinedAlert" class="btn btn-outline btn-primary">
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

// 주어진 객체
const data = {
  coupleListId: 1,
  title: 'ㅇㅁㄴ',
  dataStart: new Date('2024-02-12T09:00:00'),
  dataEnd: new Date('2024-02-15T09:00:00'),
};

// 필요한 필드만을 가진 새로운 객체 생성
const newData = {
  coupleListId: data.coupleListId,
  title: data.title,
  dataStart: data.dataStart,
  dataEnd: data.dataEnd,
};

console.log(newData);

const params = ref({
  _sort: 'createdAt', // 무엇을
  _order: 'desc', // 내림차순
});

const showCombinedAlert = async () => {
  let isSave = false;
  const result = await Swal.fire({
    title: 'Do you want to save the changes?',
    input: 'textarea',
    inputLabel: 'Message',
    inputPlaceholder: 'Type your message here...',
    inputAttributes: {
      'aria-label': 'Type your message here',
    },
    showDenyButton: true,
    showCancelButton: true,
    confirmButtonText: 'Save',
    denyButtonText: `Don't save`,
  });

  if (result.isConfirmed) {
    isSave = true;
    Swal.fire('Saved!', '', 'success');
  } else if (result.isDenied) {
    Swal.fire('Changes are not saved', '', 'info');
  }

  if (isSave && result.value) {
    console.log(result.value);
  }
};

const showAlert2 = async () => {
  const { value: text } = await Swal.fire({
    input: 'textarea',
    inputLabel: 'Message',
    inputPlaceholder: 'Type your message here...',
    inputAttributes: {
      'aria-label': 'Type your message here',
    },
    showCancelButton: true,
  });
  if (text) {
    Swal.fire(text);
  }
};

const showAlert = async () => {
  Swal.fire({
    title: 'Do you want to save the changes?',
    showDenyButton: true,
    showCancelButton: true,
    confirmButtonText: 'Save',
    denyButtonText: `Don't save`,
  }).then(result => {
    /* Read more about isConfirmed, isDenied below */
    if (result.isConfirmed) {
      Swal.fire('Saved!', '', 'success');
    } else if (result.isDenied) {
      Swal.fire('Changes are not saved', '', 'info');
    }
  });
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
