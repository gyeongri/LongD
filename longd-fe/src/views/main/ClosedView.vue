<template>
  <div class="desktop">
    <div class="div">
      <p class="p">
        <span class="text-wrapper">
          초기 비밀번호는 생일입니다. 비밀번호 변경은 프로필에서 가능합니다.
          <br />
          비밀번호를 잊어버리셨다면?
        </span>
        <button @click="resetpassword()" class="span">비밀번호초기화</button>
      </p>
      <div class="text-wrapper-2">비밀번호 입력</div>
      <div class="long-d">
        여러분의 장거리 연애 도우미
        <br />
        Long-:D
      </div>
      <div class="group">
        <input
          v-for="(password, index) in inputRefs"
          :key="index"
          :value="displayValues[index]"
          :class="`password-input${index}`"
          maxlength="1"
          @input="handleInput(index)"
          :ref="inputRefs[index]"
          v-model="inputRefs[index]"
        />
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref, watch } from 'vue';
import { onBeforeRouteLeave, useRouter } from 'vue-router';
import { useMainDisplayStore } from '@/stores/maindisplay.js';
import { useUserStore } from '@/stores/user.js';
import Swal from 'sweetalert2';
import { resetClosedPasswords, checkSimplePassword } from '@/utils/api/user';
const count = ref(0);
const mainDisplayStore = useMainDisplayStore();
const userStore = useUserStore();
const router = useRouter();
//화면비번
const displayValues = ref(['', '', '', '']);

//실제비번
const inputRefs = ref(['', '', '', '']);

const handleInput = index => {
  if (inputRefs.value[index]) {
    if (!/^\d*$/.test(inputRefs.value[index])) {
      inputRefs.value[index] = '';
      const inputElement = document.querySelector(`.password-input${index}`);
      inputElement.focus();
      Swal.fire('숫자만 입력해주세요');

      return;
    }
    displayValues.value[index] = '🤍';
    const nextIndex = index + 1;
    if (nextIndex <= 3) {
      focusNextInput(nextIndex);
    } else {
      inputRefs.value.forEach((element, index) => {
        console.log(index, element);
        if (element == '') {
          const inputElement = document.querySelector(
            `.password-input${index}`,
          );
          inputElement.focus();
          Swal.fire('모두 입력해주세요');
        }
      });
    }
  }
};
watch(inputRefs.value, (newValues, oldValues) => {
  count.value = 0;
  console.log('왜안되니');
  newValues.forEach((element, index) => {
    console.log(index, typeof element, element);
    if (element != '' && !isNaN(element)) {
      count.value++;
    }
  });
});
watch(count, (newValues, oldValues) => {
  if (newValues == 4) {
    checkpassword();
  }
});
const checkpassword = function () {
  const payload = inputRefs.value.join('');
  checkSimplePassword(payload, success => {
    const result = success.data;
    console.log(payload, result);
    if (result == '정수가 아닌값이 감지되었습니다.') {
      displayValues.value = ['', '', '', ''];
      inputRefs.value.forEach((element, index) => {
        inputRefs.value[index] = '';
      });
      const inputElement = document.querySelector(`.password-input0`);
      inputElement.focus();
      count.value = 0;
      Swal.fire('숫자만 적어주세요!');
    } else if (result == '비밀번호가 다릅니다.') {
      displayValues.value = ['', '', '', ''];
      inputRefs.value.forEach((element, index) => {
        inputRefs.value[index] = '';
      });
      const inputElement = document.querySelector(`.password-input0`);
      inputElement.focus();
      count.value = 0;
      Swal.fire('비밀번호가 틀립니다!');
    } else if (result == '비밀번호 인증 성공') {
      mainDisplayStore.closedPage = false;
      router.go(-1);
    }
  });
  // if (inputRefs.value.join('') == userStore.getUserState.passwordSimple) {
  //   mainDisplayStore.closedPage = false;
  //   router.go(-1);
  // } else {
  //   displayValues.value = ['', '', '', ''];
  //   inputRefs.value.forEach((element, index) => {
  //     inputRefs.value[index] = '';
  //   });
  //   const inputElement = document.querySelector(`.password-input0`);
  //   inputElement.focus();
  //   count.value = 0;
  //   Swal.fire('비밀번호가 틀립니다!');
  // }
};
const resetpassword = () => {
  resetClosedPasswords(
    success => {
      console.log('화면잠금 비밀번호 초기화 완료');
      console.log(success.data);
      Swal.fire('비밀번호 초기화 완료');
      displayValues.value.forEach((_, i) => (displayValues[i] = ''));
      inputRefs.value = ['', '', '', ''];
      router.push({ name: 'Closed' });
    },
    error => {
      console.log('비밀번호 초기화 실패', error);
    },
  );
};
//다른곳 가는거 방지
onBeforeRouteLeave((to, from, next) => {
  if (mainDisplayStore.closedPage == true) {
    return;
  }
  next();
  // ...
});

const focusNextInput = index => {
  const inputElement = document.querySelector(`.password-input${index}`);
  inputElement.focus();
};
onMounted(() => {
  const inputElement = document.querySelector('.password-input0');
  inputElement.focus();
});
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

.password-input0 {
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

.password-input1 {
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

.password-input2 {
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

.password-input3 {
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
