<template>
  <div class="container mx-auto flex">
    <div class="flex-1">
      <TheHeader></TheHeader>
      <TheView></TheView>
    </div>
    <div v-if="isChatting" class="border-4 border-red-300 w-1/4">
      <TheChatting></TheChatting>
    </div>
    <div v-else class="border-4 border-red-300 w-1/10">
      <TheNochatting></TheNochatting>
    </div>
    <ViduMainView class="hihi"></ViduMainView>
  </div>
</template>
<script setup>
import TheHeader from '@/layouts/TheHeader.vue';
import TheView from '@/layouts/TheView.vue';
import TheChatting from './layouts/TheChatting.vue';
import TheNochatting from './layouts/TheNochatting.vue';
import ViduMainView from './views/openvidu/ViduMainView.vue';

const isChatting = true;
// if, else로 하지 말고, 버전 1,2,3으로 구분해서 채팅관련된 것이 아예 없도록 하던가 하면 될 듯.
</script>

<style scoped>
.hihi {
  visibility: hidden;
  height: 0.2px;
  width: 1px;
  display: flex;
}
</style>
