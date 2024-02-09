<template>
  <div>
    <h1>about 페이지</h1>
    <!-- <div class="stack">
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
    </div> -->
    <button @click="showAlert" class="btn btn-outline btn-primary">
      Hello world
    </button>
    <button @click="showAlert2" class="btn btn-outline btn-primary">
      Hello world
    </button>
    <button @click="showAlert3" class="btn btn-outline btn-primary">
      Hello world
    </button>
    <button @click="showCombinedAlert" class="btn btn-outline btn-primary">
      Hello world
    </button>
  </div>
</template>

<script setup>
// import { getAlbums } from '@/utils/api/albums';
import { ref, watchEffect } from 'vue';
// import GalleryCard from '@/components/gallery/GalleryCard.vue';
import Swal from 'sweetalert2';

// 주어진 객체

// 필요한 필드만을 가진 새로운 객체 생성
// <<<<<<< HEAD
// const newData = {
//   coupleListId: data.coupleListId,
//   title: data.title,
//   dataStart: data.dataStart,
//   dataEnd: data.dataEnd,
// };

// console.log(newData);

// const params = ref({
//   _sort: 'createdAt', // 무엇을
//   _order: 'desc', // 내림차순
// });

// const showCombinedAlert = async () => {
//   let isSave = false;
//   const result = await Swal.fire({
//     title: 'Do you want to save the changes?',
//     input: 'textarea',
//     inputLabel: 'Message',
//     inputPlaceholder: 'Type your message here...',
//     inputAttributes: {
//       'aria-label': 'Type your message here',
//     },
//     showDenyButton: true,
//     showCancelButton: true,
//     confirmButtonText: 'Save',
//     denyButtonText: `Don't save`,
//   });

//   if (result.isConfirmed) {
//     isSave = true;
//     Swal.fire('Saved!', '', 'success');
//   } else if (result.isDenied) {
//     Swal.fire('Changes are not saved', '', 'info');
//   }

//   if (isSave && result.value) {
//     console.log(result.value);
//   }
// };

// const showAlert3 = async () => {
//   const { value: title } = await Swal.fire({
//     title: '당신의 추억을 입력해주세요.',
//     input: 'text',
//     inputLabel: '당신의 추억 제목',
//     inputPlaceholder: '추억을 입력해주세요.',
//     showCancelButton: true,
//   });

//   const { value: color } = await Swal.fire({
//     title: 'Select color',
//     input: 'select',
//     inputOptions: {
//       red: 'red',
//       orange: 'orange',
//       yellow: 'yellow',
//       green: 'green',
//       blue: 'blue',
//       indigo: 'indigo',
//       violet: 'violet',
//       purple: 'purple',
//       pink: 'pink',
//       brown: 'brown',
//       black: 'black',
//     },
//     inputPlaceholder: 'Select a color',
//     showCancelButton: true,
//   });
//   if (title && color) {
//     Swal.fire('Saved!', '', 'success');
//   }
//   return [title, color];
// };

// const showAlert2 = async () => {
//   const { value: color } = await Swal.fire({
//     title: 'Select color',
//     input: 'select',
//     inputOptions: {
//       red: 'red',
//       orange: 'orange',
//       yellow: 'yellow',
//       green: 'green',
//       blue: 'blue',
//       indigo: 'indigo',
//       violet: 'violet',
//       purple: 'purple',
//       pink: 'pink',
//       brown: 'brown',
//       black: 'black',
//     },
//     inputPlaceholder: 'Select a color',
//     showCancelButton: true,
//   });
//   if (color) {
//     Swal.fire(`You selected: ${color}`);
//   }
// };

// const showAlert = async () => {
//   const { value: title } = await Swal.fire({
//     title: '당신의 추억을 입력해주세요.',
//     input: 'text',
//     inputLabel: '당신의 추억 제목',
//     inputPlaceholder: '추억을 입력해주세요.',

const showAlert = async () => {
  const Toast = Swal.mixin({
    toast: true,
    position: 'top-end',
    showConfirmButton: false,
    timer: 1000,
    didOpen: toast => {
      toast.onmouseenter = Swal.stopTimer;
      toast.onmouseleave = Swal.resumeTimer;
    },
  });
  Toast.fire({
    icon: 'success',
    title: 'Signed in successfully',
  });
  if (title) {
    Swal.fire('Saved!', '', 'success');
    return title;
  }
};

// const fetchAlbums = async () => {
//   try {
//     const { data } = await getAlbums(params.value);
//     items.value = data;
//   } catch (err) {
//     console.error(err);
//   }
// };

// watchEffect(fetchAlbums);
</script>
