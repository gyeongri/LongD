<template>
  <div class="isolate bg-white px-6 py-15 sm:py-15 lg:px-8">
    <div class="mx-auto max-w-2xl text-center">
      <h2 class="text-3xl font-bold tracking-tight text-gray-900 sm:text-4xl">
        상대방과 연결하기
      </h2>
      <p class="mt-2 text-lg leading-8 text-gray-600">
        상대의 정보를 입력해주세요! 나의 정보가 아닌
        <strong>상대의 정보</strong>를 입력해야 연결이 됩니다. 둘 중 한 명만
        입력하면 됩니다. <br />
        <strong>* 나의 연결 코드 : {{ userStore.getUserState.code }}</strong>
      </p>
    </div>
    <div class="grid grid-cols-1 gap-x-8 gap-y-6 sm:grid-cols-2">
      <div class="sm:col-span-2">
        <label
          for="name"
          class="block text-sm font-semibold leading-6 text-gray-900"
          >상대의 이름</label
        >
        <div class="mt-2.5">
          <input
            type="text"
            v-model="checkInfo.name"
            name="name"
            id="name"
            autocomplete="name"
            class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
          />
        </div>
      </div>
    </div>
    <div class="grid grid-cols-1 gap-x-8 gap-y-6 sm:grid-cols-2">
      <div class="sm:col-span-2">
        <label
          for="birth"
          class="block text-sm font-semibold leading-6 text-gray-900"
          >상대의 생년월일</label
        >
        <div class="mt-2.5">
          <input
            type="date"
            v-model="checkInfo.birth"
            name="birth"
            id="birth"
            autocomplete="birth"
            class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
          />
        </div>
      </div>
    </div>
    <div class="grid grid-cols-1 gap-x-8 gap-y-6 sm:grid-cols-2">
      <div class="sm:col-span-2">
        <label
          for="email"
          class="block text-sm font-semibold leading-6 text-gray-900"
          >상대의 이메일</label
        >
        <div class="mt-2.5">
          <input
            type="email"
            v-model="checkInfo.email"
            name="email"
            id="email"
            autocomplete="email"
            class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
          />
        </div>
      </div>
    </div>
    <div class="grid grid-cols-1 gap-x-8 gap-y-6 sm:grid-cols-2">
      <div class="sm:col-span-2">
        <label
          for="code"
          class="block text-sm font-semibold leading-6 text-gray-900"
          >상대가 전달해 준 연결코드</label
        >
        <div class="mt-2.5">
          <input
            type="number"
            v-model="checkInfo.code"
            name="code"
            id="code"
            autocomplete="code"
            class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
          />
        </div>
      </div>
    </div>
    <div class="mt-10">
      <button
        @click="choiceDate"
        class="block w-full rounded-md bg-stone-600 px-3.5 py-2.5 text-center text-sm font-semibold text-white shadow-sm hover:bg-stone-400 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-stone-600"
      >
        제출
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import router from '@/router';
import {
  coupleMatching,
  coupleDataGet,
  coupleDataModify,
} from '@/utils/api/user';
import { useUserStore } from '@/stores/user.js';
import Swal from 'sweetalert2';

const checkInfo = ref({
  name: '',
  birth: '',
  email: '',
  code: '',
});
const coupleData = ref();
const userStore = useUserStore();
const startDay = ref();
const choiceDate = async () => {
  coupleMatching(
    checkInfo.value,
    success => {
      console.log(success.data);
      if (success.data === '코드가 일치하지 않습니다.') {
        Swal.fire(
          '코드가 일치하지 않습니다. 상대방이 전해준 코드가 맞는지 확인해주세요.',
        );
        console.log(checkInfo.value);
      }
      if (success.data === '상대방 이름 또는 생일이 일치하지 않습니다.') {
        Swal.fire(
          '상대방 이름 또는 생일이 일치하지 않습니다. 다시 확인해주세요.',
        );
        console.log(checkInfo.value);
      }
      if (success.data === '상대방이 coupleListId를 가지고 있는 상태입니다.') {
        Swal.fire('이미 다른 사람과 연결된 사람입니다. 다시 확인해주세요.');
        console.log(checkInfo.value);
      }
      if (success.data === '상대방이 존재하지 않습니다.') {
        Swal.fire(
          '동일한 이메일 정보를 가진 사람이 없습니다. 다시 확인해주세요.',
        );
      }
      if (success.data === '커플리스트를 만드는데 성공했습니다.') {
        coupleDataGet(
          success => {
            coupleData.value = success.data;
            console.log(coupleData.value);
          },
          async success2 => {
            const { value: date } = await Swal.fire({
              title: '여러분이 처음 만난 날을 입력해주세요.',
              input: 'date',
              didOpen: () => {},
              preConfirm: () => {
                const selectedDate = Swal.getInput().value;
                const today = new Date().toISOString().split('T')[0];
                console.log(selectedDate);
                if (!selectedDate) {
                  Swal.showValidationMessage('날짜를 선택해주세요');
                  return false;
                } else if (selectedDate > today) {
                  Swal.showValidationMessage('만난일은 미래일 수 없습니다.');
                  return false;
                }
              },
            });
            startDay.value = date;
            if (startDay.value) {
              await Swal.fire(
                '여러분의 처음 만난 날은',
                `${startDay.value}입니다.`,
              );
              coupleData.value.startDay = startDay.value;
              console.log(startDay.value);
              console.log(coupleData.value.startDay);
              coupleDataModify(
                coupleData.value,
                success => {
                  console.log(coupleData.value);
                  router.push({ name: 'Home' });
                },
                error => {
                  Swal.fire('수정되지않았어요!', error);
                  console.log('커플 정보가 수정되지 않음');
                },
              );
            } else {
              console.log(coupleData.value);
              Swal.fire('날짜가 입력되지 않았어요.');
            }
          },
          error => {
            console.log('커플리스트 정보 조회 불가능', error);
          },
        );
      } else {
        console.log('보낸 값을 확인해보자!');
      }
    },
    error => {
      console.log('커플매칭 되지 않음', error);
    },
  );
};
</script>

<style scoped></style>
