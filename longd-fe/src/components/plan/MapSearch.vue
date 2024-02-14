<template>
  <div
    class="container max-h-[600px] border-2 border-red-200 rounded-lg overflow-y-auto"
  >
    <h2 class="ml-4 text-lg">검색결과</h2>
    <ul class="p-4">
      <li
        v-for="place in planStore?.searchResults"
        :key="place.place_id"
        class="mb-4"
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

  <!-- 누르면 아래가 보이는 형태 ver -->
  <!-- <div class="collapse bg-base-200">
          <input type="checkbox" />
          <div class="collapse-title text-xl font-medium">
            <strong>{{ place.name }}</strong
            ><br />
            {{ place.address }}
          </div>
  
          <div class="collapse-content">
            <p>주소: {{ place.address }}</p>
            <p>위도: {{ place.geometry.location.lat() }}</p>
            <p>경도: {{ place.geometry.location.lng() }}</p>
          </div>
        </div> -->
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
