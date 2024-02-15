<template>
  <div>
    <div class="flex justify-end">
      <button
        v-if="!viduStore.isrecoding"
        @click="viduStore.startRecording"
        class="btn btn-sm mr-1 bg-red-200 hover:bg-red-400"
      >
        녹화 시작
      </button>
      <button
        v-else
        @click="viduStore.stopRecording(coupleid)"
        class="btn btn-sm mr-1 bg-red-200 hover:bg-red-400"
      >
        녹화 종료
      </button>
      <button @click="callChildMethod()" class="px-2">
        <img alt="Pip" src="/static/img/pip_icon.png" class="w-9 h-9" />
      </button>
    </div>
    <div class="flex flex-col">
      <div class="flex gap-3">
        <div
          class="w-1/2 aspect-ratio shadow-xl flex justify-center items-center rounded-xl"
        >
          <ViduMine v-if="viduStore.publisherTest" />
          <div v-else class="noChatting">
            아래 버튼을 통화버튼을 눌러 영상 통화를 시작해주세요.
          </div>
        </div>
        <div
          class="w-1/2 aspect-ratio shadow-xl flex justify-center items-center rounded-xl"
        >
          <ViduYours v-if="viduStore.hasSubscriber" ref="viduYoursRef" />
          <div v-else>
            <video v-if="videosrc != false" controls>
              <source
                :src="videosrc"
                type="video/webm"
                class="object-cover overflow-hidden rounded-xl"
              />
            </video>

            <div v-else class="noChatting">
              같이 찍은 영상으로 대기 영상 지정이 가능해요.
            </div>
          </div>
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
import { computed, onMounted, ref } from 'vue';
import { getVideo } from '@/utils/api/albums';
const videosrc = computed(() => {
  if (videoList.value.length > 0) {
    return videoList.value[0].pathUrl;
  }
  return false;
});
const userStore = useUserStore();
const viduStore = useViduStore();
const videoList = ref([]);
const viduYoursRef = ref();
const callChildMethod = () => {
  viduYoursRef.value.enterPiPMode();
};
const coupleid = ref('');
const join = function () {
  console.log('조인할때 coupleid', coupleid.value);
  viduStore.joinSession(coupleid.value);
};
const disconnect = function () {
  viduStore.removeUser();
  viduStore.leaveSession();
  viduStore.closeSession();
  viduStore.subscriber = '';
  viduStore.publisher = '';
  viduStore.publisherTest = '';
};
const params = ref({
  _limit: 6, // 몇개씩 조회
  _page: 1, // 현재 페이지를 조회
  _sort: 'id', // 무엇을
  _order: 'desc', // 내림차순
  // id_like: '', // 해당 요소 검색 기능
});
onMounted(() => {
  if (userStore.getUserState?.coupleListId !== undefined) {
    coupleid.value = String(userStore.getUserState?.coupleListId);
  }
  getVideo(params.value)
    .then(res => {
      videoList.value = res.data;
    })
    .catch(error => {
      console.error(error);
    });
});
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
.noChatting {
  font-size: 35px;
}
.aspect-ratio {
  aspect-ratio: 4 / 3; /* 640x480 ratio */
}
</style>
