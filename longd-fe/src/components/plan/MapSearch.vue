<template>
  <div class="border-2 border-red-200 rounded-lg">
    <h2>검색 결과</h2>
  </div>
  <ul>
    <!-- 검색 결과 클릭 시 handleResultClick 함수 호출 -->
    <li
      v-for="place in planStore?.searchResults"
      :key="place.place_id"
      @click="handleResultClick(place)"
    >
      <strong>{{ place.name }}</strong
      ><br />
      {{ place.address }}
      <!-- 장소 상세 정보 확인 버튼 -->
      <!-- <button @click="planStore.addHopeList(place)">자세히 보기</button> -->
      <button @click="planStore.addHopeList(place)">❤</button>

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
    </li>
  </ul>
</template>

<script setup>
import { usePlanStore } from '@/stores/plan.js';
const planStore = usePlanStore();
</script>

<style scoped></style>
