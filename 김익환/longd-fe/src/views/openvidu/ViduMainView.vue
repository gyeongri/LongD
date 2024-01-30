<template>
  <div class="desktop">
    <button @click="join(coupleid)">통화하기</button>

    <div class="div">
      <ViduMine />
      <div>
        <ViduYours v-if="viduStore.hasSubscriber" />
        <div v-else class="rectangle-2"></div>
      </div>
    </div>
  </div>
</template>

<script setup>
import ViduMine from '@/components/openvidu/ViduMine.vue';
import ViduYours from '@/components/openvidu/ViduYours.vue';
import { useViduStore } from '@/stores/vidu.js';
import { ref } from 'vue';
const viduStore = useViduStore();
const coupleid = ref('aa');
const join = function (coupleid) {
  viduStore.joinSession(coupleid);
};
</script>

<style>
.desktop {
  background-color: #ffffff;
  display: flex;
  flex-direction: row;
  justify-content: center;
  width: 100%;
}

.desktop .div {
  background-color: #ffffff;
  height: 1024px;
  position: relative;
  width: 1440px;
}

.desktop .rectangle {
  background-color: #d9d9d9;
  height: 617px;
  left: 113px;
  position: absolute;
  top: 177px;
  width: 493px;
}

.desktop .rectangle-2 {
  background-color: #d9d9d9;
  height: 617px;
  left: 807px;
  position: absolute;
  top: 177px;
  width: 493px;
}
</style>
