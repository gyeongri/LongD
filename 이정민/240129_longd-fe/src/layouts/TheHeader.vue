<template>
  <div class="navbar bg-base-100">
    <div>
      <RouterLink class="btn btn-ghost text-xl" :to="{ name: 'Home' }"
        >롱디</RouterLink
      >
    </div>

    <div class="flex-none">
      <ul class="menu menu-horizontal px-1">
        <li>
          <RouterLink :to="{ name: 'GalleryList' }"
            ><img
              class="image"
              alt="Gallery"
              src="/public/static/img/image.png"
          /></RouterLink>
        </li>
        <li>
          <RouterLink :to="{ name: 'Map' }">
            <img class="image-3" alt="map" src="/public/static/img/1.png" />
          </RouterLink>
        </li>
        <!-- <li>
          <details>
            <summary>profile</summary>
            <ul class="p-2 bg-base-100 rounded-t-none">
              <li><RouterLink :to="{ name: 'Home' }">Home</RouterLink></li>
              <li><RouterLink :to="{ name: 'About' }">About</RouterLink></li>
            </ul>
          </details>
        </li> -->
      </ul>
    </div>

    <div @click="lockPage()">
      <!-- <RouterLink :to="{ name: 'closed' }"> 화면잠금 </RouterLink> -->
      <!-- 화면잠금할게아니라 어떤 변수를 위로 던져줘야한다! -->
      <p>화면잠금</p>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
const closedPage = ref(false);
const lockPage = () => {
  closedPage.value = true;
  // App.vue로 이동시키기
};
</script>

<style scoped></style>
