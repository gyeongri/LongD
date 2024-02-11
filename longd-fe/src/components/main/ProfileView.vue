<template>
  <div class="isolate bg-white px-6 py-15 sm:py-15 lg:px-8">
    <div class="mx-auto max-w-2xl text-center">
      <h2 class="text-3xl font-bold tracking-tight text-gray-900 sm:text-4xl">
        {{ userStore.getUserState?.nickname }}님의 프로필 페이지
      </h2>
    </div>
    <button class="btn btn-rose" @click="goHome">돌아가기</button>

    <!-- 프로필 사진 -->
    <div>
      <img :src="userStore.getUserState?.profilePicture" alt="MyImage" />
    </div>

    <!-- 상태 메세지 -->
    <div class="sm:col-span-2">
      <label
        for="message"
        class="block text-sm font-semibold leading-6 text-gray-900"
        >상태 메세지</label
      >
      <div class="mt-2.5">
        {{ userStore.getUserState?.profileMessage }}
      </div>
    </div>

    <!-- 이름 -->
    <div class="grid grid-cols-1 gap-x-8 gap-y-6 sm:grid-cols-2">
      <div class="sm:col-span-2">
        <label
          for="name"
          class="block text-sm font-semibold leading-6 text-gray-900"
          >이름</label
        >
        <div class="mt-2.5">
          {{ userStore.getUserState?.name }}
        </div>
      </div>
    </div>

    <!-- 생년월일 -->
    <div class="grid grid-cols-1 gap-x-8 gap-y-6 sm:grid-cols-2">
      <div class="sm:col-span-2">
        <label
          for="birth"
          class="block text-sm font-semibold leading-6 text-gray-900"
          >생년월일</label
        >
        <div class="mt-2.5">
          {{ userStore.getUserState?.birth }}
        </div>
      </div>
    </div>

    <!-- 이메일 -->
    <div class="grid grid-cols-1 gap-x-8 gap-y-6 sm:grid-cols-2">
      <div class="sm:col-span-2">
        <label
          for="email"
          class="block text-sm font-semibold leading-6 text-gray-900"
          >이메일</label
        >
        <div class="mt-2.5">
          {{ userStore.getUserState?.email }}
        </div>
      </div>
    </div>
    <!-- 사는 곳 -->
    <div class="grid grid-cols-1 gap-x-8 gap-y-6 sm:grid-cols-2">
      <div class="sm:col-span-2">
        <label
          for="email"
          class="block text-sm font-semibold leading-6 text-gray-900"
          >사는 곳</label
        >
        <div class="mt-2.5">
          {{ userStore.getUserState?.address }}
        </div>
      </div>
    </div>

    <!-- 화면잠금 비밀번호 -->
    <div class="grid grid-cols-1 gap-x-8 gap-y-6 sm:grid-cols-2">
      <div class="sm:col-span-2">
        <label
          for="closedPassword"
          class="block text-sm font-semibold leading-6 text-gray-900"
          >화면잠금 비밀번호</label
        >
        <div class="mt-2.5">
          <!-- v-for를 써서 myprofile.closedPassword 해야한다 -->
          <!-- 초기 비밀번호면 myprofile.passwordSimple로 넣도록 해야하는지..? 아니면 화면잠금에서 생일로 설정했다가 이거는 다시 비밀번호변경할때만 바꾸는건지? -->
          {{ userStore.getUserState?.passwordSimple }}
        </div>
      </div>
    </div>

    <!-- 수정 -->
    <div class="mt-10">
      <button
        @click="correctionDate"
        class="block w-full rounded-md bg-stone-600 px-3.5 py-2.5 text-center text-sm font-semibold text-white shadow-sm hover:bg-stone-400 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-stone-600"
      >
        수정
      </button>
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

<style scoped></style>
