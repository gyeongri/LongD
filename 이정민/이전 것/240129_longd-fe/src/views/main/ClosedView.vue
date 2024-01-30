<template>
  <div class="desktop">
    <div class="div">
      <p class="p">
        <span class="text-wrapper">
          초기 비밀번호는 생일입니다. 비밀번호 변경은 프로필에서 가능합니다.
          <br />
          비밀번호를 잊어버리셨다면?
        </span>
        <span class="span">비밀번호초기화</span>
      </p>
      <div class="text-wrapper-2">비밀번호 입력</div>
      <div class="long-d">
        여러분의 장거리 연애 도우미
        <br />
        Long-:D
      </div>
      <div class="group">
        <input
          v-model="passwords[0]"
          @input="handleInput(0)"
          maxlength="1"
          class="password-input1"
        />
        <input
          v-model="passwords[1]"
          @input="handleInput(1)"
          maxlength="1"
          class="password-input2"
        />
        <input
          v-model="passwords[2]"
          @input="handleInput(2)"
          maxlength="1"
          class="password-input3"
        />
        <input
          v-model="passwords[3]"
          @input="handleInput(3)"
          maxlength="1"
          class="password-input4"
        />
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue';
// import { watch } from 'vue';
// import { useFocus } from '@vueuse/core';

const passwords = reactive(['', '', '', '']);
const inputRefs = ref([]);
// const finalPasswords = ref('')
// const { focused } = useFocus(passwords);

const handleInput = index => {
  if (passwords[index] && index < 3) {
    inputRefs.value.push(passwords[index]);
    passwords[index] = '❤️';
    // watch(focused, focused => {
    //   if (focused) console.log('input element has been focused');
    //   else console.log('input element has lost focus');
    // });

    passwords[`${index + 1}`].focus();
  } else if (passwords[index]) {
    inputRefs.value.push(passwords[index]);
    passwords[index] = '❤️';
    console.log('Password:', inputRefs.value.join(''));
  }
};

// 관찰자 써서 그대로 넣을 수 있게 하는 것!
// watch(passwords, (newValue, oldValue) => {
//   if (newValue) {
//     inputRefs.value.push(passwords.value)
//   }
//   // 누를때마다 넣는 것!
//   else {
//     const index = checkTrue.value.indexOf(true)
//     // true가 있는 인덱스를 찾아서
//     checkTrue.value.splice(index,1)
//     // 해당 인덱스의 값을 지우기!
//   }
// })
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
</style>
