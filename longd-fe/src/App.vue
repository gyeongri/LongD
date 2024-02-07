<template>
  <div class="container mx-auto">
    <TheHeader></TheHeader>
    <!-- <div class="ase"> -->
    <TheView></TheView>
    <!-- <ChatMainView class="big"></ChatMainView> -->
    <!-- </div> -->
    <ViduMainView class="hihi"></ViduMainView>
  </div>
</template>
<script setup>
import TheHeader from '@/layouts/TheHeader.vue';
import TheView from '@/layouts/TheView.vue';
import ViduMainView from './views/openvidu/ViduMainView.vue';
import ChatMainView from '@/views/chat/ChatMainView.vue';
</script>

<style scoped>
.ase {
  display: flex;
}
.big {
  height: 60%;
  width: 30%;
}
.hihi {
  visibility: hidden;
  height: 0.2px;
  width: 1px;
  display: flex;
}
</style>
