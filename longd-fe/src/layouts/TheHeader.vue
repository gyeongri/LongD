  <template>
    <div v-show="!logOutPage && !closedPage" class="navbar bg-base-100">
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
        </ul>
        <!-- 드롭다운 형식인거 -->
        <!-- <li>
            <details>
              <summary>profile</summary>
              <ul class="p-2 bg-base-100 rounded-t-none">
                <li><RouterLink :to="{ name: 'Home' }">Home</RouterLink></li>
                <li><RouterLink :to="{ name: 'About' }">About</RouterLink></li>
              </ul>
            </details>
          </li> -->
      </div>
    </div>
  </template>

  <script setup>
  import { ref } from 'vue';
  import { useRouter } from 'vue-router';
  import { useDisplayStore } from '@/stores/display.js';

  const router = useRouter();
  const displayStore = useDisplayStore();

  const closedPage = ref(false);
  const lockPage = () => {
    closedPage.value = true;
    router.push({ name: 'Closed' });
  };
  const logOutPage = ref(false);
  const logOut = () => {
    logOutPage.value = true;
    router.push({ name: 'Login' });
  };
  </script>

  <style scoped>
  .image {
    width: 40px;
    height: 40px;
  }

  .togglebox {
    width: 200px;
    height: 200px;
  }
  </style>
