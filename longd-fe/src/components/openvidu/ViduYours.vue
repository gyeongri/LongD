<template>
  <div>
    <video class="rounded-lg" ref="videoElement" autoplay></video>
  </div>

  <button @click="enterPiPMode">pip변신</button>
  <!-- <button @click="check">여기,이제 필요없음</button> -->
</template>

<script setup>
import { computed, watch, ref, onMounted } from 'vue';
import { useViduStore } from '@/stores/vidu.js';
import { onBeforeRouteLeave, onBeforeRouteUpdate } from 'vue-router';
const viduStore = useViduStore();
const videoElement = ref();
// const check = function () {
//   console.log(viduStore.hasSubscriber);
//   viduStore.subscriber.addVideoElement(videoElement.value);
// };
const exitPiPMode = async () => {
  try {
    if (document.exitPictureInPicture) {
      await document.exitPictureInPicture();
    } else {
      console.error('PiP 모드를 종료할 수 없습니다.');
    }
  } catch (error) {
    console.error('PiP 모드 종료 중 오류가 발생했습니다:', error);
  }
};
onMounted(() => {
  viduStore.subscriber.addVideoElement(videoElement.value);
  if (document.pictureInPictureElement) {
    exitPiPMode();
  }
});
// watch(
//   () => viduStore.hasSubscriber,
//   (newValue, oldValue) => {
//     console.log('김익환');
//     if (viduStore.hasSubscriber) {
//       viduStore.subscriber.addVideoElement(videoElement.value);
//     }
//   },
// );

const enterPiPMode = async () => {
  try {
    if (document.pictureInPictureEnabled) {
      await videoElement.value.requestPictureInPicture();
    } else {
      console.error('PiP 모드를 지원하지 않는 브라우저입니다.');
    }
  } catch (error) {
    console.error('PiP 모드 진입 중 오류가 발생했습니다:', error);
  }
};

onBeforeRouteLeave((to, from, next) => {
  if (videoElement.value) {
    enterPiPMode();
    setTimeout(() => {
      next();
    }, 50);
  } else {
    next();
  }
});
</script>

<style scoped>
.video {
  border: 1px solid black;
}
</style>
