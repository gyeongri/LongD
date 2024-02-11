<template>
  <div v-for="place in planStore?.hopeList" :key="place.place_id">
    <div class="stats shadow">
      <div class="stat">
        <!-- <div class="stat-title">Total Page Views</div> -->
        <div class="stat-value">{{ place.title }}</div>
        <!-- <div class="stat-desc">21% more than last month</div> -->
      </div>
    </div>
  </div>

  <div>
    여행일정 나오게하기
    <h2>제목 입력하기</h2>
    <h3>날짜지정하기 => 즐겨찾기 목록 아래에 달력 만들어지기</h3>
    <p>즐겨찾기 목록 보이기</p>
    <p>달력 만들어지면 즐겨찾기 목록에서 달력으로 가지고 오기</p>
    <p>데이터 담기고 나면 저장해서 여행목록 리스트로 보내기</p>
  </div>
  <div></div>
</template>

<script setup>
import { usePlanStore } from '@/stores/plan';
const planStore = usePlanStore();
</script>

<style scoped></style>
