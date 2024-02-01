<template>
  <div class="isolate bg-white px-6 py-15 sm:py-15 lg:px-8">
  <div class="mx-auto max-w-2xl text-center">
      <h2 class="text-3xl font-bold tracking-tight text-gray-900 sm:text-4xl">상대방과 연결하기</h2>
      <p class="mt-2 text-lg leading-8 text-gray-600">상대의 정보를 입력해주세요! 나의 정보가 아닌 상대의 정보를 입력해야 연결이 됩니다. 둘 중 한 명만 입력하면 됩니다.</p>
    </div>
    <div class="grid grid-cols-1 gap-x-8 gap-y-6 sm:grid-cols-2">
      <div class="sm:col-span-2">
        <label for="name" class="block text-sm font-semibold leading-6 text-gray-900">상대의 이름</label>
          <div class="mt-2.5">
            <input type="text" v-model="checkInfo.name" name="name" id="name" autocomplete="name" class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" />
          </div>
      </div>
    </div>
    <div class="grid grid-cols-1 gap-x-8 gap-y-6 sm:grid-cols-2">
      <div class="sm:col-span-2">
        <label for="birth" class="block text-sm font-semibold leading-6 text-gray-900">상대의 생년월일</label>
          <div class="mt-2.5">
            <input type="date" v-model="checkInfo.birth" name="birth" id="birth" autocomplete="birth" class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" />
          </div>
      </div>
    </div>
    <div class="grid grid-cols-1 gap-x-8 gap-y-6 sm:grid-cols-2">
      <div class="sm:col-span-2">
        <label for="email" class="block text-sm font-semibold leading-6 text-gray-900">상대의 이메일</label>
          <div class="mt-2.5">
            <input type="email" v-model="checkInfo.email" name="email" id="email" autocomplete="email" class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" />
          </div>
      </div>
    </div>
    <div class="grid grid-cols-1 gap-x-8 gap-y-6 sm:grid-cols-2">
      <div class="sm:col-span-2">
        <label for="code" class="block text-sm font-semibold leading-6 text-gray-900">상대가 전달해 준 연결코드</label>
          <div class="mt-2.5">
            <input type="text" v-model="checkInfo.code" name="code" id="code" autocomplete="code" class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" />
          </div>
      </div>
    </div>
    <div class="mt-10">
        <button @click="choiceDate" class="block w-full rounded-md bg-stone-600 px-3.5 py-2.5 text-center text-sm font-semibold text-white shadow-sm hover:bg-stone-400 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-stone-600">제출</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import router from '@/router';
import Swal from 'sweetalert2';

const checkInfo = ref({})

const choiceDate = async () => {
  // 백으로 보내주기 확인차원에서!
  // if 이름, 생일, 이메일, 연결코드가 동일하면 아래 실행, 동일하지 않으면 틀렸다고 뜨기
  const { value: date } = await Swal.fire({
    title: '여러분이 처음 만난 날을 입력해주세요.',
    input: 'date',
    didOpen: () => {
      const today = new Date().toISOString();
      Swal.getInput().min = today.split('T')[0];
    },
  });
  if (date) {
    Swal.fire('아래 날짜가 맞나요?', date);
    // 화면 전환(DB로 보내주고 - 이거는 메인화면에서 날짜 설정한 거 써야해..!)
  } else {
    Swal.fire('날짜가 입력되지 않았어요.');
  };
  // 날짜 입력된 후에 가능하도록 하기!
  router.push({name:'Home'})
};
</script>

<style scoped></style>
