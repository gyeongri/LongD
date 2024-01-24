<template>
  <video autoplay/>
</template>

<script setup>
import { onMounted, ref } from 'vue';
const props = defineProps(['streamManager']);

const { addVideoElement } = props.streamManager;
const videoElement = ref(null);

onMounted(() => {
  addVideoElement(videoElement.value);
});
</script>
