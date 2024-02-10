<template>
  <div class="navbar bg-base-100">
    <div class="navbar-start">
      <RouterLink class="btn btn-ghost text-xl" :to="{ name: 'Home' }"
        >롱디</RouterLink
      >
    </div>
    <div class="navbar-center">
      <ul class="menu menu-horizontal font-bold">
        <li>
          <RouterLink :to="{ name: 'GalleryFolder' }">
            갤러리
            <!-- <img class="image" alt="Gallery" src="/static/img/image.png"/> -->
          </RouterLink>
        </li>
        <li>
          <RouterLink :to="{ name: 'Calendar' }"
            >캘린더
            <!-- <img class="image" alt="Calendar" src="/static/img/2x.png"/> -->
          </RouterLink>
        </li>
        <li>
          <details>
            <summary>
              화상통화
              <!-- <img class="image" alt="ViduMain" src="/static/img/im.png" /> -->
            </summary>
            <ul class="p-2 bg-base-100 rounded-t-none">
              <li><RouterLink :to="{ name: 'ViduMain' }">main</RouterLink></li>
              <li>
                <RouterLink :to="{ name: 'ViduVideo' }">같이 보기</RouterLink>
              </li>
            </ul>
          </details>
        </li>
        <li>
          <RouterLink :to="{ name: 'TestMap' }">맵TEST</RouterLink>
        </li>
        <li>
          <RouterLink :to="{ name: 'Map' }"
            >여행짜기
            <!-- <img class="image" alt="Map" src="/static/img/1.png"/> -->
          </RouterLink>
        </li>
      </ul>
    </div>
    <div class="navbar-end">
      <ul class="menu menu-horizontal font-bold">
        <li @click="lockPage()">
          <span>화면잠금</span>
        </li>
        <li @click="logOut()">
          <span>로그아웃</span>
        </li>
        <li>
          <details>
            <summary>profile</summary>
            <ul class="p-2 bg-base-100 rounded-t-none">
              <li><RouterLink :to="{ name: 'Home' }">Home</RouterLink></li>
              <li><RouterLink :to="{ name: 'About' }">About</RouterLink></li>
            </ul>
          </details>
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import { useRouter } from 'vue-router';
import { logout } from '@/utils/api/user.js';
import { useUserStore } from '@/stores/user.js';
import { useMainDisplayStore } from '@/stores/maindisplay.js';
import { useUserStore } from '@/stores/user.js';

const router = useRouter();
const userStore = useUserStore();
const mainDisplayStore = useMainDisplayStore();
const userStore = useUserStore();
// const closedPage = ref(false);
const lockPage = () => {
  mainDisplayStore.closedPage = true;
  router.push({ name: 'Closed' });
};
// const logOutPage = ref(false);
const logOut = () => {
  userStore.setUserState('');
  logout(
    success => {
      // router.push({ name: 'Login' });
      console.log('로그아웃 성공');
    },
    fail => {
      console.log('logout 오류 : ' + fail);
      // router.push({ name: 'Login' });
    },
  );
};
</script>

<style scoped>
.image {
  width: 40px;
  height: 40px;
}
</style>
