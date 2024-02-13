<template>
  <div>
    <div class="flex justify-end">
      <button @click="viduStore.startRecording">
        <font-awesome-icon
          icon="fa-solid fa-record-vinyl"
          size="2x"
          color="red"
        />
      </button>
      <button @click="viduStore.stopRecording(coupleid.value)">
        <font-awesome-icon
          icon="fa-solid fa-record-vinyl"
          size="2x"
          fade
          color="red"
        />
      </button>
      <button @click="enterPiPMode" class="px-2">
        <img alt="Pip" src="/static/img/pip_icon.png" class="w-9 h-9" />
      </button>
    </div>
    <div class="flex">
      <ViduMine />
      <div class="mx-4">
        <ViduYours v-if="viduStore.hasSubscriber" />
        <div v-else class="rectangle-2"></div>
      </div>
    </div>
    <div class="bottomBar flex justify-center">
      <label class="swap">
        <!-- this hidden checkbox controls the state -->
        <input type="checkbox" @click="viduStore.toggleAudio" />

        <!-- volume on icon -->
        <div class="icon-container swap-on">
          <font-awesome-icon
            icon="fa-solid fa-volume-high"
            size="lg"
            class="text-white"
          />
        </div>

        <!-- volume off icon -->
        <div class="icon-container swap-off">
          <font-awesome-icon
            icon="fa-solid fa-volume-xmark"
            size="lg"
            class="text-white"
          />
        </div>
      </label>
      <label class="swap">
        <!-- this hidden checkbox controls the state -->
        <input type="checkbox" />

        <!-- call on icon -->
        <div class="icon-container swap-on rectangle-icon" @click="join()">
          <font-awesome-icon
            icon="fa-solid fa-phone-slash"
            size="lg"
            class="text-white"
          />
        </div>
        <!-- call off icon -->
        <div
          class="icon-container swap-off rectangle-icon"
          @click="disconnect()"
        >
          <font-awesome-icon
            icon="fa-solid fa-phone"
            size="lg"
            class="text-white"
          />
        </div>
      </label>
      <label class="swap">
        <!-- this hidden checkbox controls the state -->
        <input type="checkbox" @click="viduStore.toggleVideo" />

        <!-- video on icon -->
        <div class="icon-container swap-on">
          <font-awesome-icon
            icon="fa-solid fa-video"
            size="lg"
            class="text-white"
          />
        </div>

        <!-- video off icon -->
        <div class="icon-container swap-off">
          <font-awesome-icon
            icon="fa-solid fa-video-slash"
            size="lg"
            class="text-white"
          />
        </div>
      </label>
    </div>
    <!-- <div>
      화면공유하면 이거 살려서 컴포넌트 구성
    </div> -->
    <!-- <button v-else @click="disconnect()">볼륨조절</button> -->
  </div>
</template>

<script setup>
import ViduMine from '@/components/openvidu/ViduMine.vue';
import ViduYours from '@/components/openvidu/ViduYours.vue';
import { useViduStore } from '@/stores/vidu.js';
import { useUserStore } from '@/stores/user.js';
import { ref } from 'vue';
const userStore = useUserStore();
const viduStore = useViduStore();
const coupleid = ref('');
const join = function () {
  coupleid.value = useUserStore.getUserState?.coupleListId;
  viduStore.joinSession(coupleid.value);
};
const disconnect = function () {
  viduStore.removeUser();
  viduStore.leaveSession();
  viduStore.subscriber = '';
  viduStore.publisher = '';
  viduStore.publisherTest = '';
};
</script>

<style>
.icon-container {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 10px;
  background-color: #d9d9d9;
}
.icon-container font-awesome-icon {
  font-size: 24px;
}

.rectangle-icon {
  width: 60px;
  height: 40px;
  border-radius: 5px;
  background-color: rgb(243, 65, 65);
}

.rectangle-icon font-awesome-icon {
  font-size: 24px;
}

.icon-container {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 10px;
  background-color: #d9d9d9;
}
.icon-container font-awesome-icon {
  font-size: 24px;
}

.rectangle-icon {
  width: 60px;
  height: 40px;
  border-radius: 5px;
  background-color: rgb(243, 65, 65);
}

.rectangle-icon font-awesome-icon {
  font-size: 24px;
}
</style>
