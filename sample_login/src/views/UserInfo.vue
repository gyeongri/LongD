<script setup>
import { checkloginstate, logout, sendinfo } from "@/api/main";
import { useRouter } from "vue-router";
import { ref, onMounted } from "vue";

const router = useRouter();

const login_state = ref({});

onMounted(() => {
  checkloginstate(
    (data) => {
      login_state.value = data.data;
    },
    (error) => {
      console.log("onMounted 오류 : " + error);
    }
  );
});

const send = () => {
  sendinfo(
    login_state.value,
    (data) => {
      //success
      console.log("sendinfo 성공");
      router.push({ name: "loginPage"});
    },
    (error) => {
      console.log("sendinfo 오류 : " + error);
    }
  );
};
</script>

<template>
  <div>
    <p>user_id : <input type="text" class="input-text" v-model="login_state.userId" /></p>
    <p>생일(8자리) : <input type="text" class="input-text" v-model="login_state.birth" /></p>
    <p>이메일(도메인포함) : <input type="text" class="input-text" v-model="login_state.email" /></p>
    <p>성별 : <input type="text" class="input-text" v-model="login_state.gender" /></p>
    <p>이름 : <input type="text" class="input-text" v-model="login_state.name" /></p>
    <p>거주국가 : <input type="text" class="input-text" v-model="login_state.addressNation" /></p>
    <p>권한 : <input type="text" class="input-text" value="ROLE_USER" disabled /></p>
    <p>provider : <input type="text" class="input-text" v-model="login_state.provider" /></p>

    <button @click="send">정보 업데이트 해버리기</button>
    <br />
    <br />
    <br />
    <br />
    {{ login_state }}
  </div>
</template>

<style></style>
