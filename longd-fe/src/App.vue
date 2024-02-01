<template>
  <TheHeader></TheHeader>
  <TheView></TheView>
  <ViduMainView class="hihi"></ViduMainView>
</template>
<script setup>
import TheHeader from '@/layouts/TheHeader.vue';
import TheView from '@/layouts/TheView.vue';
import ViduMainView from './views/openvidu/ViduMainView.vue';
</script>

<style scoped>
.hihi {
  visibility: hidden;
  height: 0.2px;
  width: 1px;
  display: flex;
}
</style>
