<template>
  <div v-if="!userStore.isLogin">
    <!-- <div v-if="로그인이라면(토큰이 있다면)"> -->
    <div v-if="화면잠금 값이 false라면">
      <HomeView></HomeView>
      <!-- <TheView></TheView> -->
    </div>
    <div v-if="화면잠금 값이 true라면">
      <ClosedView />
    </div>
  </div>
  <div v-if="userStore.isLogin">
    <!-- <div v-if="로그아웃이라면(토큰이 없다면)"> -->
    <LoginSignUpView />
  </div>
</template>
<script setup>
import HomeView from '@/views/user/HomeView.vue';
import ClosedView from '@/views/main/ClosedView.vue';
import LoginSignUpView from '@/views/main/LoginSignUpView.vue';

// import TheView from '@/layouts/TheView.vue';
</script>

<style scoped></style>
