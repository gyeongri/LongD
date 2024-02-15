<template>
  <div class="navbar bg-base-100">
    <div class="navbar-start">
      <RouterLink class="btn btn-ghost text-5xl" :to="{ name: 'Home' }">
        <h1 class="logoFont">롱디</h1>
      </RouterLink>
    </div>
    <div class="navbar-center">
      <ul class="menu menu-horizontal font-bold">
        <li>
          <a>
            <RouterLink
              :to="{ name: 'GalleryFolder' }"
              :class="{ 'text-red-300': isGalleryActive }"
            >
              갤러리
            </RouterLink>
          </a>
        </li>
        <li>
          <a>
            <RouterLink
              :to="{ name: 'Calendar' }"
              :class="{ 'text-red-300': isCalendarActive }"
              >캘린더
            </RouterLink>
          </a>
        </li>
        <li>
          <details ref="dropdown1">
            <summary
              :class="{ 'text-red-300': isViduMainActive || isViduVideoActive }"
            >
              화상통화
            </summary>
            <ul
              class="p-2 bg-base-100 rounded-t-none"
              style="z-index: 999"
              @click="closeDropdown"
            >
              <li>
                <RouterLink
                  :to="{ name: 'ViduMain' }"
                  :class="{ 'text-red-300': isViduMainActive }"
                  ><a>통화하기</a></RouterLink
                >
              </li>
              <li>
                <RouterLink
                  :to="{ name: 'ViduVideo' }"
                  :class="{ 'text-red-300': isViduVideoActive }"
                  ><a>같이보기</a></RouterLink
                >
              </li>
            </ul>
          </details>
        </li>
        <li>
          <details ref="dropdown2">
            <summary
              :class="{ 'text-red-300': isMapActive || isPlanListActive }"
            >
              여행✈
            </summary>

            <ul
              class="p-2 bg-base-100 rounded-t-none"
              style="z-index: 999"
              @click="closeDropdown2"
            >
              <li>
                <RouterLink
                  :to="{ name: 'Map' }"
                  :class="{ 'text-red-300': isMapActive }"
                  ><a>일정계획</a></RouterLink
                >
              </li>
              <li>
                <RouterLink
                  :to="{ name: 'PlanList' }"
                  :class="{ 'text-red-300': isPlanListActive }"
                >
                  <a>여행목록</a></RouterLink
                >
              </li>
            </ul>
          </details>
        </li>
        <li>
          <RouterLink
            :to="{ name: 'bucketList' }"
            :class="{ 'text-pink-400': isBucketListActive }"
            ><a> 버킷리스트</a>
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
const isMapActive = computed(
  () =>
    router.currentRoute.value.name === 'MapSearch' ||
    router.currentRoute.value.name === 'MapPlan',
);
const isPlanListActive = computed(
  () =>
    router.currentRoute.value.name === 'PlanList' ||
    router.currentRoute.value.name === 'PlanDetail',
);

const isViduMainActive = computed(
  () => router.currentRoute.value.name === 'ViduMain',
);

const isViduVideoActive = computed(
  () => router.currentRoute.value.name === 'ViduVideo',
);

const lockPage = () => {
  mainDisplayStore.closedPage = true;
  router.push({ name: 'Closed' });
};

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
  dropdown2.value.removeAttribute('open');
}

function closeDropdown2() {
  dropdown1.value.removeAttribute('open');
  dropdown2.value.removeAttribute('open');
}
</script>

<style scoped>
a,
summary,
span {
  font-size: 24px;
}
.image {
  width: 60px;
  height: 60px;
}
.logoFont {
  font-size: 35px;
}
</style>
