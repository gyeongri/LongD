<template>
  <TheHeader></TheHeader>
  <ViduMainView class="hihi"></ViduMainView>
  <TheView></TheView>
</template>
<script setup>
import TheHeader from '@/layouts/TheHeader.vue';
import TheView from '@/layouts/TheView.vue';
import ViduMainView from './views/openvidu/ViduMainView.vue';
</script>

<style scoped>
.hihi {
  visibility: hidden;
  height: 1px;
  width: 1px;
  display: flex;
}
</style>
