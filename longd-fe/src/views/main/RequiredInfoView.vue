<template>
  <div class="isolate bg-white px-6 py-15 sm:py-15 lg:px-8">
    <div class="mx-auto max-w-2xl text-center">
      <h2 class="text-3xl font-bold tracking-tight text-gray-900 sm:text-4xl">
        필수 정보 입력
      </h2>
      <p class="mt-2 text-lg leading-8 text-gray-600">
        아래 필수 정보를 확인하고, 수정 후 제출 버튼을 눌러주세요.
      </p>
    </div>
    <form action="#" method="POST" class="mx-auto mt-10 max-w-xl sm:mt-10">
      <div class="grid grid-cols-1 gap-x-8 gap-y-6 sm:grid-cols-2">
        <div>
          <label class="btn btn-primary" for="img">프로필 사진 업로드</label>
          <input
            type="file"
            id="img"
            autocomplete="img"
            @change="fileUpload"
            hidden
          />
          <img
            v-if="Info_state.profilePicture"
            :src="Info_state.profilePicture"
            alt="Uploaded Image"
          />
        </div>
        <!-- ID -->
        <div class="sm:col-span-2">
          <label
            for="nickname"
            class="block text-sm font-semibold leading-6 text-gray-900"
            >애칭</label
          >
          <div class="mt-2.5">
            <input
              type="text"
              v-model="Info_state.nickname"
              name="nickname"
              id="nickname"
              autocomplete="nickname"
              class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
            />
          </div>
        </div>
        <!-- 이름 -->
        <div class="sm:col-span-2">
          <label
            for="name"
            class="block text-sm font-semibold leading-6 text-gray-900"
            >이름</label
          >
          <div class="mt-2.5">
            <input
              type="text"
              v-model="Info_state.name"
              name="name"
              id="name"
              autocomplete="name"
              class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
            />
          </div>
        </div>
        <!-- 생년월일 -->
        <div class="sm:col-span-2">
          <label
            for="birth"
            class="block text-sm font-semibold leading-6 text-gray-900"
            >생년월일(8글자)</label
          >
          <div class="mt-2.5">
            <input
              type="date"
              v-model="Info_state.birth"
              name="birth"
              id="birth"
              autocomplete="birthday"
              class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
            />
          </div>
        </div>
        <!-- 이메일 -->
        <div class="sm:col-span-2">
          <label
            for="email"
            class="block text-sm font-semibold leading-6 text-gray-900"
            >Email</label
          >
          <div class="mt-2.5">
            <input
              type="email"
              v-model="Info_state.email"
              name="email"
              id="email"
              autocomplete="email"
              class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
            />
          </div>
        </div>
        <!-- 거주국가랑 성별도 입력해야함 -->
        <div class="sm:col-span-2">
          <label
            for="gender"
            class="block text-sm font-semibold leading-6 text-gray-900"
            >성별</label
          >
          <select
            v-model="Info_state.gender"
            id="gender"
            name="gender"
            class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
          >
            <option value="선택안함">선택안함</option>
            <option value="남성">남성</option>
            <option value="여성">여성</option>
          </select>
          <ChevronDownIcon
            class="pointer-events-none absolute right-3 top-0 h-full w-5 text-gray-400"
            aria-hidden="true"
          />
        </div>
        <!-- 거주국이랑 거주도시는 값 입력이 안되어있다 이거 저장할 수 있도록 하기! -->
        <div>
          <label
            for="addressNation"
            class="block text-sm font-semibold leading-6 text-gray-900"
            >거주국</label
          >
          <select
            v-model="Info_state.address_nation"
            id="addressNation"
            name="addressNation"
            class="h-full rounded-md border-0 bg-transparent bg-none py-0 pl-4 pr-9 text-gray-900 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm"
          >
            <option selected disabled>나라를 골라주세요</option>
            <option value="한국">한국</option>
            <option value="영국">영국</option>
          </select>
        </div>
        <div>
          <label
            for="addressCity"
            class="block text-sm font-semibold leading-6 text-gray-900"
            >거주도시</label
          >
          <!-- 나중에 나라 고르면 도시 선택하도록 만들기 -->
          <select
            v-model="Info_state.address_city"
            id="addressCity"
            name="addressCity"
            class="h-full rounded-md border-0 bg-transparent bg-none py-0 pl-4 pr-9 text-gray-900 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm"
          >
            <option selected disabled>도시를 골라주세요</option>
            <option value="서울">서울</option>
            <option value="대구">대구</option>
          </select>
        </div>

        <!-- 이거 DB로 보내서 확인시키기 -->
        <br />
        <div class="sm:col-span-2">
          <strong>♡ 상대에게 전달할 연결코드를 정해주세요.</strong>
        </div>
        <div>
          <label
            for="code"
            class="block text-sm font-semibold leading-6 text-gray-900"
            >연결코드</label
          >
          <div class="mt-2.5">
            <input
              type="number"
              v-model="Info_state.code"
              name="code"
              id="code"
              autocomplete="code"
              class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
            />
          </div>
        </div>
        <!-- 코드 확인 -->
        <div>
          <label
            for="codeCheck"
            class="block text-sm font-semibold leading-6 text-gray-900"
            >확인용(한 번 더 입력해주세요.)</label
          >
          <div class="mt-2.5">
            <input
              type="number"
              v-model="codeCheck"
              name="codeCheck"
              id="codeCheck"
              autocomplete="codeCheck"
              class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
            />
          </div>
        </div>
      </div>
      <div class="mt-10">
        <button
          type="submit"
          @click.submit.prevent="send"
          class="block w-full rounded-md bg-stone-600 px-3.5 py-2.5 text-center text-sm font-semibold text-white shadow-sm hover:bg-stone-400 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-stone-600"
        >
          제출
        </button>
      </div>
    </form>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { ChevronDownIcon } from '@heroicons/vue/20/solid';
import { BaseInfo, sendinfo } from '@/utils/api/user';
import { uploadImage } from '@/utils/api/photo';
import { useRouter } from 'vue-router';
import { useUserStore } from '@/stores/user.js';
import Swal from 'sweetalert2';

const router = useRouter();
const userStore = useUserStore();
const Info_state = ref({});
const codeCheck = ref();

const fileUpload = event => {
  const formData = new FormData();
  formData.append('file', event.target.files[0]);
  uploadImage(
    formData,
    success => {
      Info_state.value.profilePicture = success.data[0];
    },
    error => {
      console.log('사진을 변환할 수 없어요.', error);
    },
  );
};

onMounted(() => {
  BaseInfo(
    data => {
      Info_state.value = data.data;
    },
    error => {
      console.log('Base Info 가져오기 안됨', error);
    },
  );
});

const send = () => {
  if (Info_state.value.code === codeCheck.value) {
    if (
      Info_state.value.nickname &&
      Info_state.value.name &&
      Info_state.value.email &&
      Info_state.value.birth &&
      Info_state.value.code
    ) {
      sendinfo(
        Info_state.value,
        data => {
          console.log('sendinfo 성공 & 로그인 값 넣기');
          userStore.setUserState(data.data);
          router.push({ name: 'ConnectCode' });
        },
        error => {
          console.log('sendinfo 오류 : ' + error);
        },
      );
    } else {
      Swal.fire({
        icon: 'error',
        title: '입력되지 않은 항목이 있습니다. 전부 입력해주세요.',
      });
    }
  } else {
    Swal.fire({
      icon: 'error',
      title: '연결코드 입력값이 서로 달라요!',
    });
  }
};
</script>
