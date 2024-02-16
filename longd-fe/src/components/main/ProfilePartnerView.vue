<template>
  <div class="bg-white p-6">
    <div class="flex justify-center">
      <div>
        <div class="mb-6 text-center">
          <h2
            class="text-3xl font-bold tracking-tight text-gray-900 sm:text-4xl"
          >
            {{ partnerInfo.nickname }}님의 프로필 페이지
          </h2>
          <div class="flex justify-items-start">
            <button class="img-btn mt-1 mr-2" @click="goHome"></button>
          </div>
        </div>

        <div class="flex justify-center">
          <img :src="partnerInfo.profilePicture" alt="상대 이미지" />
          <!-- {{ partnerInfo.profilePicture }} -->
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
          <h3 class="block text-sm font-semibold leading-6 text-gray-900">
            이름
          </h3>
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

        <div class="sm:col-span-2">
          <h3 class="block text-sm font-semibold leading-6 text-gray-900">
            사는 곳
          </h3>
          <div class="mt-2.5">
            <span
              class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
              >{{ partnerInfo.address }}</span
            >
          </div>
        </div>
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

<style scoped>
img {
  height: 25%;
  width: 25%;
}
.img-btn {
  background-image: url('/static/img/arrow2.png');
  background-size: contain; /* 이미지가 버튼 크기에 맞게 조정됩니다 */
  background-position: center; /* 이미지가 중앙에 위치하도록 합니다 */
  background-repeat: no-repeat;
  /* right: 0.5rem;
  bottom: 0.5rem; */
  width: 2rem; /* 버튼의 너비 */
  height: 2rem; /* 버튼의 높이 */
  border: none; /* 버튼의 기본 테두리 제거 */
}
</style>
