<template>
  <div class="isolate bg-white px-6 py-15 sm:py-15 lg:px-8">
    <div class="mx-auto max-w-2xl text-center">
      <h2 class="text-3xl font-bold tracking-tight text-gray-900 sm:text-4xl">
        {{ userInfo.nickname }}님의 프로필 수정 페이지
      </h2>
      <div class="flex justify-items-start">
        <button class="img-btn mt-1 mr-2" @click="goHome"></button>
      </div>
    </div>

    <!-- 프로필 사진 -->
    <div class="text-center">
      <div class="flex justify-center">
        <img
          v-if="userInfo.profilePicture"
          :src="userInfo?.profilePicture"
          alt="Uploaded Image"
        />
      </div>
      <div>
        <label class="btn mt-3" for="uploadImage">이미지 업로드</label>
        <input type="file" id="uploadImage" @change="fileUpload" hidden />
      </div>
    </div>

    <!-- 상태 메세지 -->
    <div class="sm:col-span-2">
      <label
        for="message"
        class="block text-sm font-semibold leading-6 text-gray-900"
        >상태 메세지</label
      >
      <div class="mt-2.5">
        <textarea
          v-model="userInfo.profileMessage"
          name="message"
          id="message"
          rows="4"
          class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
        ></textarea>
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
          <input
            type="text"
            v-model="userInfo.name"
            name="name"
            id="name"
            autocomplete="name"
            class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
          />
        </div>
      </div>
    </div>

    <!-- 닉네임 -->
    <div class="grid grid-cols-1 gap-x-8 gap-y-6 sm:grid-cols-2">
      <div class="sm:col-span-2">
        <label
          for="nickname"
          class="block text-sm font-semibold leading-6 text-gray-900"
          >닉네임</label
        >
        <div class="mt-2.5">
          <input
            type="text"
            v-model="userInfo.nickname"
            name="nickname"
            id="nickname"
            autocomplete="nickname"
            class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
          />
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
          <input
            type="date"
            v-model="userInfo.birth"
            name="birth"
            id="birth"
            autocomplete="birth"
            class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
          />
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
          <input
            type="email"
            v-model="userInfo.email"
            name="email"
            id="email"
            autocomplete="email"
            class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
          />
        </div>
      </div>
    </div>

    <!-- 나라랑 도시 -->
    <div>
      <label
        for="addressNation"
        class="block text-sm font-semibold leading-6 text-gray-900"
        >사는 곳</label
      >
      <select
        v-model="userInfo.address"
        id="addressNation"
        name="addressNation"
        class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
      >
        <option selected disabled>나라를 골라주세요</option>
        <option
          v-for="option in nationList"
          :key="option.id"
          :value="option.name"
        >
          {{ option.name }}
        </option>
      </select>
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
          <!-- v-for를 써서 userStore.closedPassword 해야한다 -->
          <input
            type="text"
            v-model="userInfo.passwordSimple"
            @input="handlePasswordInput"
            name="closedPassword[index]"
            id="closedPassword"
            autocomplete="closedPassword"
            class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
          />
          <p>
            * 화면잠금 비밀번호는 4자리 숫자로 입력해주세요. 조건에 맞지 않으면
            저장되지 않습니다.
          </p>
        </div>
      </div>
    </div>

    <!-- 저장 -->
    <div class="mt-10">
      <button
        @click="choiceDate"
        class="block w-full rounded-md bg-stone-600 px-3.5 py-2.5 text-center text-sm font-semibold text-white shadow-sm hover:bg-stone-400 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-stone-600"
      >
        저장
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import router from '@/router';
import { sendinfo, getNationList } from '@/utils/api/user';
import { uploadImage } from '@/utils/api/photo';
import { useUserStore } from '@/stores/user.js';
import Swal from 'sweetalert2';

const userStore = useUserStore();
const userInfo = userStore.getUserState;
const nationList = ref();
const handlePasswordInput = () => {
  // 입력값이 4자리를 초과하면 마지막 자리를 제거
  if (
    userInfo.passwordSimple &&
    userInfo.passwordSimple.toString().length > 4
  ) {
    userInfo.passwordSimple = parseInt(
      userInfo.passwordSimple.toString().slice(0, 4),
    );
  }
};
const goHome = () => {
  router.push({ name: 'Home' });
};

const fileUpload = event => {
  const formData = new FormData();
  formData.append('file', event.target.files[0]);
  console.log(event.target.files[0]);
  uploadImage(
    formData,
    success => {
      userInfo.profilePicture = success.data[0]['pathUrl'];
    },
    success2 => {
      console.log('사진 변환 완료!');
    },
    error => {
      console.log('사진을 변환할 수 없어요.', error);
    },
  );
};

//이메일형식확인용
const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

const choiceDate = () => {
  console.log(userInfo);
  const today = new Date().toISOString().split('T')[0];
  if (userInfo.birth > today) {
    Swal.fire('생년월일을 확인해주세요');
    return;
  }
  if (!emailRegex.test(userInfo.email)) {
    Swal.fire('이메일 형식을 확인해주세요');
    return;
  }
  if (userInfo.passwordSimple.length !== 4) {
    Swal.fire('화면잠금 비밀번호를 확인해주세요');
    return;
  }

  sendinfo(
    userInfo,
    success => {
      console.log('Sendinfo success!');
      router.push({ name: 'Profile' });
    },
    error => {
      console.log('sendinfo 오류 : ' + error);
    },
  );
};

onMounted(() => {
  getNationList(
    success => {
      nationList.value = success.data;
    },
    error => {
      console.log(error);
    },
  );
  if (userStore.getUserState?.passwordSimple) {
    console.log(userStore.getUserState.passwordSimple);
    if (userStore.getUserState.passwordSimple.toString().length === 3) {
      userInfo.passwordSimple = '0' + userStore.getUserState.passwordSimple;
    }
  }
});
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
img {
  height: 25%;
  width: 25%;
}
</style>
