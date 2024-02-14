<template>
  <div class="container mx-auto" :class="{ 'chrome-zoom': isChrome }">
    <TheHeader
      v-show="
        userStore.isLogin &&
        !mainDisplayStore.isClosed &&
        userStore.getUserState?.coupleListId
      "
    ></TheHeader>
    <div class="flex">
      <div class="flex-1 mr-8">
        <TheView></TheView>
      </div>

      <div
        v-if="
          userStore.isLogin &&
          !mainDisplayStore.isClosed &&
          userStore.getUserState?.coupleListId
        "
        :class="{
          'w-1/4': isChatting,
          'w-1/10': !isChatting,
        }"
      >
        <div v-if="isChatting" class="fixed">
          <TheChatting @offChat="chatFalse"></TheChatting>
        </div>
        <div v-else>
          <TheNochatting @onChat="chatTrue"></TheNochatting>
        </div>
        <ViduMainView class="hihi"></ViduMainView>
      </div>
    </div>
  </div>
</template>
<script setup>
import TheHeader from '@/layouts/TheHeader.vue';
import TheView from '@/layouts/TheView.vue';
import TheChatting from './layouts/TheChatting.vue';
import TheNochatting from './layouts/TheNochatting.vue';
import ViduMainView from './views/openvidu/ViduMainView.vue';
import { useUserStore } from '@/stores/user.js';
import { useMainDisplayStore } from '@/stores/maindisplay.js';
import { ref, onMounted } from 'vue';
const userStore = useUserStore();
const mainDisplayStore = useMainDisplayStore();
const isChatting = ref(true);
const chatFalse = function () {
  isChatting.value = false;
};
const chatTrue = function () {
  isChatting.value = true;
};
const isChrome = ref(false);
onMounted(() => {
  const userAgent = navigator.userAgent.toLowerCase();
  isChrome.value =/chrome/.test(userAgent) && !/edge|edg\/|opr\//.test(userAgent);
});
// if, else로 하지 말고, 버전 1,2,3으로 구분해서 채팅관련된 것이 아예 없도록 하던가 하면 될 듯.
</script>

<style scoped>
.hihi {
  visibility: hidden;
  height: 0.2px;
  width: 1px;
  display: flex;
}
.check {
  display: none;
}
.chrome-zoom {
  zoom: 92%;
}
</style>

<style>
@font-face {
  font-family: 'TTHakgyoansimKkokkomaR';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2402_keris@1.0/TTHakgyoansimKkokkomaR.woff2')
    format('woff2');
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: 'UhBeemysen';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_five@.2.0/UhBeemysen.woff')
    format('woff');
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: 'omyu_pretty';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2304-01@1.0/omyu_pretty.woff2')
    format('woff2');
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: 'Beeunhye';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/naverfont_01@1.0/Beeunhye.woff')
    format('woff');
  font-weight: normal;
  font-style: normal;
}
body,
html {
  /* font-family: 'TTHakgyoansimKkokkomaR', sans-serif; */
  /* font-family: 'omyu_pretty', sans-serif; */
  font-family: 'Beeunhye', sans-serif;
  font-size: 20px;
}
</style>
