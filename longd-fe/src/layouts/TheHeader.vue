<template>
  <div v-show="mainDisplayStore.isVisible" class="navbar bg-base-100">
    <div>
      <RouterLink class="btn btn-ghost text-xl" :to="{ name: 'Home' }"
        >롱디</RouterLink
      >
    </div>
    <div>
      <ul class="menu menu-horizontal px-1">
        <li>
          <RouterLink :to="{ name: 'GalleryList' }"
            ><img class="image" alt="Gallery" src="/static/img/image.png"
          /></RouterLink>
        </li>
        <li><RouterLink :to="{ name: 'Calendar' }">Calendar</RouterLink></li>
        <li>
          <RouterLink :to="{ name: 'Calendar2' }"
            ><img class="image" alt="Calendar" src="/static/img/2x.png"
          /></RouterLink>
        </li>
        <li>
          <RouterLink :to="{ name: 'ViduMain' }"
            ><img class="image" alt="ViduMain" src="/static/img/im.png"
          /></RouterLink>
        </li>
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
import { logout } from '@/utils/api/user';
import { useMainDisplayStore } from '@/stores/maindisplay.js';

const router = useRouter();
const mainDisplayStore = useMainDisplayStore();

// const closedPage = ref(false);
const lockPage = () => {
  mainDisplayStore.closedPage = true;
  router.push({ name: 'Closed' });
};
// const logOutPage = ref(false);
const logOut = () => {
  mainDisplayStore.logOutPage = true;
  logout(
    () => {
      router.push({ name: 'Login' });
    },
    fail => {
      console.log('sendinfo 오류 : ' + fail);
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
