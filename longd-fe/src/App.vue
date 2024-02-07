<template>
  <div class="container mx-auto">
    <TheHeader></TheHeader>
    <TheView></TheView>
    <TheFooter></TheFooter>
    <ViduMainView class="hihi"></ViduMainView>
  </div>
</template>
<script setup>
import TheHeader from '@/layouts/TheHeader.vue';
import TheView from '@/layouts/TheView.vue';
import TheFooter from '@/layouts/TheFooter.vue';
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
