<template>
  <div
    class="container max-h-[600px] border-2 border-red-200 rounded-lg overflow-y-auto"
  >
    <h2 class="ml-4 text-lg">
      ① 지도에서 장소 검색<br />
      ② 원하는 곳 하트 누르기<br />
      ③ '일정짜기'에서 여행계획 세우기
    </h2>
    <ul class="p-2">
      <li
        v-for="place in planStore?.searchResults"
        :key="place.place_id"
        class="mb-4 shadow-md rounded"
      >
        <strong>{{ place.name }}</strong
        ><br />
        {{ place.address }}
        <button
          @click="planStore.addHopeList(place)"
          :class="{ 'text-red-500': planStore.isHeartSelected(place) }"
          class="text-2xl"
        >
          ❤
        </button>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { usePlanStore } from '@/stores/plan.js';
const planStore = usePlanStore();
</script>

<style scoped>
/* 스크롤바 커스터마이징 */
.container::-webkit-scrollbar {
  width: 12px; /* 스크롤바 너비 */
}

.container::-webkit-scrollbar-thumb {
  background-color: #f56868; /* 스크롤바 색상 */
  border-radius: 4px; /* 스크롤바 모양 */
}

.container::-webkit-scrollbar-track {
  background-color: #cbd5e0; /* 스크롤바 트랙 색상 */
}
</style>
