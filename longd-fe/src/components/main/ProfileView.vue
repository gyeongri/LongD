<template>
  <div class="bg-white p-6">
    <div class="flex justify-center">
      <div>
        <div class="mb-6">
          <h2
            class="text-3xl font-bold tracking-tight text-gray-900 sm:text-4xl"
          >
            {{ userStore.getUserState?.nickname }}님의 프로필 페이지
          </h2>
          <div class="flex justify-items-start">
            <button class="img-btn mt-1 mr-2" @click="goHome"></button>
          </div>
        </div>

        <!-- 프로필 사진 -->
        <div>
          <img
            v-if="userStore.getUserState.profilePicture"
            :src="userStore.getUserState?.profilePicture"
            alt="My image"
          />
          <img
            v-else
            src="https://longdssafy.s3.ap-northeast-2.amazonaws.com/52efc0a1-f3fa-4e70-a803-748fd41bca7cmainIMG.png"
            alt="기본 이미지"
          />
        </div>

        <!-- 상태 메세지 -->
        <div class="sm:col-span-2">
          <h3 class="block text-sm font-semibold leading-6 text-gray-900">
            상태메세지
          </h3>
          <div class="mt-2.5">
            <span
              class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
              >{{ userStore.getUserState?.profileMessage }}</span
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
              >{{ userStore.getUserState?.name }}</span
            >
          </div>
        </div>

        <!-- 닉네임 -->
        <div class="mb-4">
          <label
            for="name"
            class="block text-sm font-semibold leading-6 text-gray-900"
            >닉네임</label
          >
          <div class="flex justify-center">
            <div class="input input-bordered w-full max-w-xs">
              {{ userStore.getUserState?.nickname }}
            </div>
          </div>
        </div>
        <!-- 생년월일 -->
        <div class="mb-4">
          <label
            for="birth"
            class="block text-sm font-semibold leading-6 text-gray-900"
            >생년월일</label
          >
          <div class="flex justify-center">
            <div class="input input-bordered w-full max-w-xs">
              {{ userStore.getUserState?.birth }}
            </div>
          </div>
        </div>

        <!-- 이메일 -->
        <div class="mb-4">
          <label
            for="email"
            class="block text-sm font-semibold leading-6 text-gray-900"
            >이메일</label
          >
          <div class="flex justify-center">
            <div class="input input-bordered w-full max-w-xs">
              {{ userStore.getUserState?.email }}
            </div>
          </div>
        </div>

        <!-- 사는 곳 -->
        <div class="mb-4">
          <label
            for="email"
            class="block text-sm font-semibold leading-6 text-gray-900"
            >사는 곳</label
          >
          <div class="flex justify-center">
            <div class="input input-bordered w-full max-w-xs">
              {{ userStore.getUserState?.address }}
            </div>
          </div>
        </div>

        <!-- 화면잠금 비밀번호 -->
        <div class="mb-4">
          <label
            for="closedPassword"
            class="block text-sm font-semibold leading-6 text-gray-900"
            >화면잠금 비밀번호</label
          >
          <div class="flex justify-center">
            <div class="input input-bordered w-full max-w-xs">****</div>
          </div>
        </div>
        <div class="mb-4 flex justify-center h-1/12">
          <button
            @click="correctionDate"
            class="btn btn-active btn-block rounded-md w-1/3 bg-stone-600 text-white shadow-sm hover:bg-stone-400 text-center"
          >
            수정
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import router from '@/router';
import { useUserStore } from '@/stores/user.js';

const userStore = useUserStore();

const goHome = () => {
  router.push({ name: 'Home' });
};

const correctionDate = () => {
  router.push({ name: 'profileCorrection' });
};
</script>

<style scoped>
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
