<template>
  <div>
    <video ref="videoElement" autoplay></video>
  </div>
  <button @click="check">여기</button>
  <button @click="viduStore.startRecording">녹화</button>
  <button @click="viduStore.stopRecording">중단</button>
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
</script>

<style scoped>
.video {
  border: 1px solid black;
}
</style>
