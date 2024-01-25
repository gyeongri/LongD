<template>
  <video ref="videoElement" autoplay />
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { defineProps } from 'vue';

const props = defineProps({
  streamManager: Object,
});

const videoElement = ref(null);

onMounted(() => {
  if (props.streamManager && videoElement.value) {
    props.streamManager.addVideoElement(videoElement.value);
  }
});
</script>
