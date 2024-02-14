<template>
  <div class="navbar bg-base-100">
    <div class="navbar-start">
      <RouterLink class="btn btn-ghost text-2xl" :to="{ name: 'Home' }"
        >롱디</RouterLink
      >
    </div>
    <div class="navbar-center">
      <ul class="menu menu-horizontal font-bold">
        <li>
          <a>
            <RouterLink
              :to="{ name: 'GalleryFolder' }"
              :class="{ 'text-pink-400': isGalleryActive }"
            >
              갤러리
              <!-- <img class="image" alt="Gallery" src="/static/img/image.png"/> -->
            </RouterLink>
          </a>
        </li>
        <li>
          <a>
            <RouterLink
              :to="{ name: 'Calendar' }"
              :class="{ 'text-pink-400': isCalendarActive }"
              >캘린더
              <!-- <img class="image" alt="Calendar" src="/static/img/2x.png"/> -->
            </RouterLink>
          </a>
        </li>
        <li>
          <details ref="dropdown1">
            <summary :class="{ 'text-pink-400': isViduActive }">
              화상통화
              <!-- <img class="image" alt="ViduMain" src="/static/img/im.png" /> -->
            </summary>
            <ul
              class="p-2 bg-base-100 rounded-t-none"
              style="z-index: 999"
              @click="closeDropdown"
            >
              <li>
                <a><RouterLink :to="{ name: 'ViduMain' }">main</RouterLink></a>
              </li>
              <li>
                <a>
                  <RouterLink :to="{ name: 'ViduVideo' }">같이보기</RouterLink>
                </a>
              </li>
            </ul>
          </details>
        </li>
        <!-- <li>
          <a>
            <RouterLink
              :to="{ name: 'TestMap' }"
              :class="{ 'text-pink-400': isTestMapActive }"
              >맵TEST</RouterLink
            >
          </a>
        </li> -->
        <li>
          <details ref="dropdown2">
            <summary
              :class="{ 'text-pink-400': isMapActive || isPlandListActive }"
            >
              여행
            </summary>
            <ul
              class="p-2 bg-base-100 rounded-t-none"
              style="z-index: 999"
              @click="closeDropdown2"
            >
              <li>
                <a
                  ><RouterLink
                    :to="{ name: 'Map' }"
                    :class="{ 'text-pink-400': isMapActive }"
                    >일정계획</RouterLink
                  ></a
                >
              </li>
              <li>
                <a>
                  <RouterLink
                    :to="{ name: 'PlanList' }"
                    :class="{ 'text-pink-400': isPlandListActive }"
                    >여행목록</RouterLink
                  >
                </a>
              </li>
            </ul>
          </details>
        </li>

        <li>
          <a>
            <RouterLink
              :to="{ name: 'bucketList' }"
              :class="{ 'text-pink-400': isBucketListActive }"
            >
              버킷리스트
              <!-- <img class="image" alt="Gallery" src="/static/img/image.png"/> -->
            </RouterLink>
          </a>
        </li>

        <!-- <li>
          <a>
            <RouterLink
              :to="{ name: 'Map' }"
              :class="{ 'text-pink-400': isMapActive }"
              >여행짜기
              <img class="image" alt="Map" src="/static/img/1.png"/>
            </RouterLink>
          </a>
        </li> -->
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
        <!-- <li>
          <details>
            <summary>profile</summary>
            <ul class="p-2 bg-base-100 rounded-t-none">
              <li>
                <a>
                  <RouterLink :to="{ name: 'Home' }">Home</RouterLink>
                </a>
              </li>
              <li>
                <a>
                  <RouterLink :to="{ name: 'About' }">About</RouterLink>
                </a>
              </li>
            </ul>
          </details>
        </li> -->
      </ul>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { computed } from 'vue';
import { useRouter } from 'vue-router';
import { logout } from '@/utils/api/user.js';
import { useUserStore } from '@/stores/user.js';
import { useMainDisplayStore } from '@/stores/maindisplay.js';

const router = useRouter();
const userStore = useUserStore();
const mainDisplayStore = useMainDisplayStore();

// 현재 라우터를 확인하는 computed 속성
const isGalleryActive = computed(
  () =>
    router.currentRoute.value.name === 'GalleryFolder' ||
    router.currentRoute.value.name === 'GalleryList' ||
    router.currentRoute.value.name === 'GalleryDetail',
);
const isCalendarActive = computed(
  () => router.currentRoute.value.name === 'Calendar',
);
// const isTestMapActive = computed(
//   () => router.currentRoute.value.name === 'TestMap',
// );
const isMapActive = computed(
  () =>
    router.currentRoute.value.name === 'MapSearch' ||
    router.currentRoute.value.name === 'MapPlan',
);
const isPlandListActive = computed(
  () =>
    router.currentRoute.value.name === 'PlanList' ||
    router.currentRoute.value.name === 'PlanDetail',
);

const isViduActive = computed(
  () =>
    router.currentRoute.value.name === 'ViduMain' ||
    router.currentRoute.value.name === 'ViduVideo',
);

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
      router.push({ name: 'Login' });
      console.log('로그아웃 성공');
    },
    fail => {
      console.log('logout 오류 : ' + fail);
      router.push({ name: 'Login' });
    },
  );
};

const dropdown1 = ref(null);
const dropdown2 = ref(null);

function closeDropdown() {
  dropdown1.value.removeAttribute('open');
}

function closeDropdown2() {
  dropdown2.value.removeAttribute('open');
}
</script>

<style scoped>
a, summary, span{
  font-size: 24px;
} 
.image {
  width: 40px;
  height: 40px;
}
</style>
