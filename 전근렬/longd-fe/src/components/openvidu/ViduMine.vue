<template>
  <ov-videoconference> </ov-videoconference>
  <div v-if="viduStore.publisher">
    <video ref="videoElement" autoplay></video>
    <canvas
      ref="canvas"
      class="canvas"
      width="640"
      height="480"
      @mousedown="startDrawing"
      @mouseup="stopDrawing"
      @mousemove="draw"
    ></canvas>
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
const canvas = ref(null);
const isDrawing = ref(false);
watch(
  () => viduStore.publisherTest,
  (newValue, oldValue) => {
    if (viduStore.publisherTest) {
      viduStore.publisher.addVideoElement(videoElement.value);
    }
  },
);
// watch(
//   () => viduStore.publisherTest,
//   (newValue, oldValue) => {
//     console.log('qwe');
//     setTimeout(() => {
//       console.log('시간차공격');
//       if (viduStore.publisherTest) {
//         viduStore.publisher.addVideoElement(videoElement.value);
//       }
//     }, 0);
//   },
// );
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

const startDrawing = () => {
  isDrawing.value = true;
};

const stopDrawing = () => {
  isDrawing.value = false;
};
const draw = event => {
  if (!isDrawing.value) return;

  const canvasElement = canvas.value;
  const ctx = canvasElement.getContext('2d');

  // 마우스 위치에서 선 그리기
  ctx.strokeStyle = 'black';
  ctx.lineWidth = 5;
  ctx.lineCap = 'round';

  ctx.lineTo(
    event.clientX - canvasElement.offsetLeft,
    event.clientY - canvasElement.offsetTop,
  );
  ctx.stroke();
  ctx.beginPath();
  ctx.moveTo(
    event.clientX - canvasElement.offsetLeft,
    event.clientY - canvasElement.offsetTop,
  );
};
</script>

<style scoped>
.video {
  border: 1px solid black;
}
.canvas {
  position: absolute;
  top: 0;
  left: 0;
  cursor: crosshair;
}
</style>
