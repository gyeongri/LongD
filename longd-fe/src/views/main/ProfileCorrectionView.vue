<template>
  <div class="isolate bg-white px-6 py-15 sm:py-15 lg:px-8">
    <div class="mx-auto max-w-2xl text-center">
      <h2 class="text-3xl font-bold tracking-tight text-gray-900 sm:text-4xl">
        프로필 페이지
      </h2>
    </div>
    <button class="btn btn-rose" @click="goHome">돌아가기</button>

    <!-- 프로필 사진 -->
    <div>
      <label class="btn btn-primary" for="uploadImage">이미지 업로드</label>
      <input type="file" id="uploadImage" @change="fileUpload" hidden />
      <img
        v-if="myprofile.profile_picture"
        :src="myprofile.profile_picture"
        alt="Uploaded Image"
      />
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
          v-model="myprofile.profile_message"
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
            v-model="myprofile.name"
            name="name"
            id="name"
            autocomplete="name"
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
            v-model="myprofile.birth"
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
            v-model="myprofile.email"
            name="email"
            id="email"
            autocomplete="email"
            class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
          />
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
          <input
            type="number"
            min="0"
            max="9"
            v-model="myprofile.password_simple"
            name="closedPassword[index]"
            id="closedPassword"
            autocomplete="closedPassword"
            class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
          />
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
import { loginstate, sendinfo } from '@/utils/api/user';

const myprofile = ref({});
const goHome = () => {
  router.push({ name: 'Home' });
};

const fileUpload = event => {
  const file = event.target.files[0];
  // 파일을 가지고오기
  if (file) {
    // FileReader를 사용하여 이미지를 읽어와 imageUrl에 할당
    const reader = new FileReader();
    // FileReader 객체 생성(파일을 비동기적으로 읽어오는 것)
    console.log(file)
    reader.onload = () => {
      // 파일의 읽기 작업이 완료되었을 때 실행할 함수
      myprofile.value.profile_picture = reader.result;
      // Base64로 인코딩된 문자열을 ref객체에 넣기
    };
    reader.readAsDataURL(file);
    // 파일을 Base64로 인코딩하여 데이터 URL로 변환
  }
  console.log(myprofile.value.profile_picture);
};

onMounted(() => {
  loginstate(
    data => {
      if (data === '롱디에 로그인 되어 있지 않음') {
        router.push({ name: 'Login' });
      } else {
        console.log('이건 된다.');
        myprofile.value = data.data;
      }
    },
    error => {
      console.log('Profile을 가져올 수 없습니다.', error);
    },
  );
});

const choiceDate = () => {
  console.log(myprofile.value);
  sendinfo(
    myprofile.value,
    success => {
      console.log('Sendinfo success!');
      router.push({ name: 'Profile' });
    },
    error => console.log('sendinfo 오류 : ' + error),
  );
};
</script>

<style scoped></style>
