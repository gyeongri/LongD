<template>
  <div class="isolate bg-white px-6 py-15 sm:py-15 lg:px-8">
    <div class="mx-auto max-w-2xl text-center">
      <h2 class="text-3xl font-bold tracking-tight text-gray-900 sm:text-4xl">
        {{ partnerInfo.nickname }}의 프로필 페이지
      </h2>
    </div>
    <button class="btn btn-primary" @click="goHome">돌아가기</button>
    <div class="sm:col-span-2">
      <h3 class="block text-sm font-semibold leading-6 text-gray-900">
        프로필 사진
      </h3>
      <div class="mt-2.5">
        <img
          v-if="partnerInfo.profilePicture"
          :src="getImageUrl(partnerInfo.value.profilePicture)"
          alt="상대 이미지"
        />
        <!-- {{ partnerInfo.profilePicture }} -->
      </div>
    </div>

    <div class="sm:col-span-2">
      <h3 class="block text-sm font-semibold leading-6 text-gray-900">
        상태메세지
      </h3>
      <div class="mt-2.5">
        <span
          class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
          >{{ partnerInfo.profileMessage }}</span
        >
      </div>
    </div>
    <div class="sm:col-span-2">
      <h3 class="block text-sm font-semibold leading-6 text-gray-900">이름</h3>
      <div class="mt-2.5">
        <span
          class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
          >{{ partnerInfo.name }}</span
        >
      </div>
    </div>
    <div class="sm:col-span-2">
      <h3 class="block text-sm font-semibold leading-6 text-gray-900">
        생년월일
      </h3>
      <div class="mt-2.5">
        <span
          class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
          >{{ partnerInfo.birth }}</span
        >
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import router from '@/router';
import { partnerinfo } from '@/utils/api/user';

const partnerInfo = ref({});
const goHome = () => {
  router.push({ name: 'Home' });
};
const getImageUrl = base64String => {
  return `data:image/jpeg;base64,${base64String}`;
};

onMounted(() => {
  partnerinfo(
    data => {
      partnerInfo.value = data.data;
    },
    error => {
      console.log('Partner Info 가져오기 안됨', error);
    },
  );
});
</script>

<style scoped></style>
