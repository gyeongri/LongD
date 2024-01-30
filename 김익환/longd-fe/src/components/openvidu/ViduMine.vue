<template>
  <ov-videoconference> </ov-videoconference>
  <div>
    <video ref="videoElement" autoplay></video>
  </div>
  <button @click="check">여기</button>
  <br />
  <button @click="viduStore.startRecording">녹화</button>
  <br />
  <button @click="viduStore.stopRecording">중단</button>
  <br />
  <button @click="enterPiPMode">pip변신</button>
</template>

<script setup>
import { computed, watch, ref } from 'vue';
import { useViduStore } from '@/stores/vidu.js';
const viduStore = useViduStore();
const videoElement = ref(null);
const check = function () {
  console.log(viduStore.publisher);
  viduStore.publisher.addVideoElement(videoElement.value);
};
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
