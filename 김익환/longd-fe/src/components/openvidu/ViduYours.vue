<template>
  <div>
    <video ref="videoElement" autoplay></video>
  </div>

  <button @click="enterPiPMode">pip변신</button>
  <button @click="click">여기</button>
</template>

<script setup>
import { computed, watch, ref } from 'vue';
import { useViduStore } from '@/stores/vidu.js';
const viduStore = useViduStore();
const videoElement = ref();
const click = function () {
  console.log('ViduYours 엘리먼트 생성1');
  viduStore.subscriber.addVideoElement(videoElement.value);
};
watch(
  // 첫 번째 인자: 감시할 데이터
  () => viduStore.hasSubscriber,

  // 두 번째 인자: 데이터가 변경될 때 실행될 콜백 함수
  (newValue, oldValue) => {
    // newValue: 변경된 데이터
    // oldValue: 변경되기  전의 데이터

    // 변경될 때 실행할 로직 작성
    if (newValue) {
      console.log('ViduYours 엘리먼트 생성2');
      viduStore.subscriber.addVideoElement(videoElement.value);
    }
  },
);

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
</script>

<style scoped>
.video {
  border: 1px solid black;
}
</style>
