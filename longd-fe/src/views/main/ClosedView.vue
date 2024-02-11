<template>
  <div class="desktop">
    <div class="div">
      <p class="p">
        <span class="text-wrapper">
          초기 비밀번호는 생일입니다. 비밀번호 변경은 프로필에서 가능합니다.
          <br />
          비밀번호를 잊어버리셨다면?
        </span>
        <button @click="removepassword()" class="span">비밀번호초기화</button>
      </p>
      <div class="text-wrapper-2">비밀번호 입력</div>
      <div class="long-d">
        여러분의 장거리 연애 도우미
        <br />
        Long-:D
      </div>
      <div class="group">
        <input
          v-for="(password, index) in passwords"
          :key="index"
          v-model="passwords[index]"
          @input="handleInput(index)"
          maxlength="1"
          :class="`password-input${index + 1}`"
        />
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, nextTick } from 'vue';
import { useRouter } from 'vue-router';
import { useMainDisplayStore } from '@/stores/maindisplay.js';
import { useUserStore } from '@/stores/user.js';
import Swal from 'sweetalert2';
import { removeClosedPasswords } from '@/utils/api/user';
// import { watch } from 'vue';
// import { useFocus } from '@vueuse/core';

const mainDisplayStore = useMainDisplayStore();
const userStore = useUserStore();
const router = useRouter();
const passwords = reactive(['', '', '', '']);
// const passwords = reactive({
//   0: ref(''),
//   1: ref(''),
//   2: ref(''),
//   3: ref(''),
// });
const inputRefs = ref([]);

// 프로필에서 사용자가 바꾼 번호로 설정해주기!
// const { focused } = useFocus(passwords);

const handleInput = index => {
  if (passwords[index] && index < 3) {
    inputRefs.value.push(passwords[index]);
    passwords[index] = '🤍';
    // watch(passwords[index], ('','❤️') => {
    //   if ('') {
    //     passwords[index].focus();
    //   } else console.log('input element has lost focus');
    // });
    // passwords[`${index + 1}`].focus();
  } else if (passwords[index]) {
    inputRefs.value.push(passwords[index]);
    passwords[index] = '🤍';
    console.log(passwords);
    // if (passwords == ['❤️', '❤️', '❤️', '❤️']) {

    if (inputRefs.value.join('') == userStore.getUserState.passwordSimple) {
      mainDisplayStore.closedPage = false;
      router.go(-1);
    } else {
      Swal.fire('비밀번호가 틀립니다!');
      console.log(inputRefs.value);
      passwords.forEach((_, i) => (passwords[i] = ''));
      inputRefs.value = [];
      router.push({ name: 'Closed' });
    }
    console.log(`Password:${passwords}`, inputRefs.value.join(''));
    passwords.value = ['', '', '', ''];
    inputRefs.value = [];
  }
};

// const handleInput = index => {
//   const password = passwords.value[index];
//   if (!/^\d$/.test(password)) {
//     passwords.value[index] = '';
//   } else {
//     if (index < passwords.value.length - 1) {
//       inputRefs.value.push(passwords[index]);
//       passwords[index] = '🤍';
//       const nextInput = document.querySelector(`.password-input${index + 2}`);
//       if (nextInput) {
//         nextInput.focus();
//       }
//     } else if (index == passwords.value.length - 1) {
//       inputRefs.value.push(passwords[index]);
//       passwords[index] = '🤍';
//       if (inputRefs.value.join('') == userStore.getUserState.passwordSimple) {
//         mainDisplayStore.closedPage = false;
//         router.go(-1);
//       } else {
//         Swal.fire('비밀번호가 틀립니다!');
//         passwords.value = ['', '', '', ''];
//         inputRefs.value = [];
//         router.push({ name: 'Closed' });
//       }
//     }
//   }
// };
// const userData = ref({});
const removepassword = () => {
  // userData.value = useUserStore.getUserState;
  // userData.value.passwordSimple = '';
  // sendinfo(
  //   userData.value,
  //   data => {
  //     console.log('sendinfo 성공 & 화면잠금 비밀번호 초기화');
  //     userStore.setUserState(data.data);
  //   },
  //   error => {
  //     console.log('sendinfo 오류 & 화면잠금 비밀번호 실패 : ' + error);
  //   },
  // );
  removeClosedPasswords(
    success => {
      console.log('화면잠금 비밀번호 초기화 완료');
    },
    error => {
      console.log('비밀번호 초기화 실패', error);
    },
  );
  // passwordSimple값 초기화시키기 = 생일로 디폴트 설정되어있음.
  // 이 정보 백으로 보내줘서 설정할 수 있도록!
};
</script>

<style scoped>
.desktop {
  background-color: #ffffff;
  display: flex;
  flex-direction: row;
  justify-content: center;
  width: 100%;
}

.desktop .div {
  background-color: #ffffff;
  height: 1024px;
  position: relative;
  width: 1440px;
}

.desktop .p {
  color: #3b3b3b9d;
  font-family: 'Inter-ExtraLight', Helvetica;
  font-size: 20px;
  font-weight: 200;
  left: 437px;
  letter-spacing: 0;
  line-height: normal;
  position: absolute;
  text-align: center;
  top: 739px;
  width: 650px;
}

.desktop .text-wrapper {
  color: #3b3b3b9d;
  font-family: 'Inter-ExtraLight', Helvetica;
  font-size: 20px;
  font-weight: 200;
  letter-spacing: 0;
}

.desktop .span {
  text-decoration: underline;
}

.desktop .text-wrapper-2 {
  color: #3b3b3b9d;
  font-family: 'Inter-ExtraLight', Helvetica;
  font-size: 30px;
  font-weight: 200;
  left: 609px;
  letter-spacing: 0;
  line-height: normal;
  position: absolute;
  text-align: center;
  top: 586px;
  width: 230px;
}

.desktop .long-d {
  color: var(--FH-pza-3);
  font-family: 'Inter-ExtraLight', Helvetica;
  font-size: 64px;
  font-weight: 200;
  left: 214px;
  letter-spacing: 0;
  line-height: normal;
  position: absolute;
  text-align: center;
  top: 250px;
  width: 1012px;
}

.desktop .group {
  height: 79px;
  left: 543px;
  position: absolute;
  top: 639px;
  width: 359px;
}

.password-input1 {
  background-color: #fff4f4;
  border: 2px solid;
  border-color: #000000;
  border-radius: 10px;
  box-shadow: 0px 4px 4px #00000040;
  height: 79px;
  left: 0;
  position: absolute;
  top: 0;
  width: 79px;
  text-align: center;
  line-height: 200px;
  font-size: 30px;
}

.password-input2 {
  background-color: #fff4f4;
  border: 2px solid;
  border-color: #000000;
  border-radius: 10px;
  box-shadow: 0px 4px 4px #00000040;
  height: 79px;
  left: 95px;
  position: absolute;
  top: 0;
  width: 79px;
  text-align: center;
  line-height: 200px;
  font-size: 30px;
}

.password-input3 {
  background-color: #fff4f4;
  border: 2px solid;
  border-color: #000000;
  border-radius: 10px;
  box-shadow: 0px 4px 4px #00000040;
  height: 79px;
  left: 185px;
  position: absolute;
  top: 0;
  width: 79px;
  text-align: center;
  line-height: 200px;
  font-size: 30px;
}

.password-input4 {
  background-color: #fff4f4;
  border: 2px solid;
  border-color: #000000;
  border-radius: 10px;
  box-shadow: 0px 4px 4px #00000040;
  height: 79px;
  left: 280px;
  position: absolute;
  top: 0;
  width: 79px;
  text-align: center;
  line-height: 200px;
  font-size: 30px;
}

/* .heart-fadeout {
  opacity: 0;
} */
</style>
