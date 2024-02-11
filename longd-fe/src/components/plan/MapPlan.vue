<template>
  <!-- 제목 입력 -->
  <label for="title">제목</label>
  <input id="title" type="text" v-model="planTitle" />
  <!-- 일정 입력하기 -->
  <div>
    <label for="start">Start Date:</label>
    <input id="start" type="date" v-model="startDate" />
    <label for="end">End Date:</label>
    <input id="end" type="date" v-model="endDate" />
    <button class="btn" @click="addRangeToList">일정추가</button>
    <button class="btn-outline" @click="clearList">일정 초기화</button>
  </div>
  <!-- 즐겨찾기 목록 -->
  <p>즐겨찾기 목록</p>
  <!-- 로컬에 저장할 수 있도록 하기 -->
  <div v-for="place in planStore?.hopeList" :key="place.place_id">
    <div class="stats shadow">
      <div class="stat">
        <div
          class="favorite-place"
          draggable="true"
          @dragstart="onDragStart(place)"
        >
          {{ place.title }}
        </div>
      </div>
    </div>
  </div>
  <!-- 날짜별 드롭하는 곳 만들기 -->
  <div v-for="(date, index) in dateList" :key="index">
    {{ date }}
    <div
      class="flex flex-col w-full min-h-200px h-auto bg-gray-400/10 justify-center items-center mt-6 rounded"
      @dragover.prevent
      @drop="onDrop($event, date)"
    >
      <div>장소들 담는 공간</div>
      <BooleanDisplay :value="isOverDropZone" />
      <div class="flex flex-wrap justify-center items-center">
        <div
          v-for="(place, idx) in placeList"
          :key="idx"
          class="w-200px bg-black-200/10 ma-2 pa-6"
        >
          <p>{{ place.name }}</p>
        </div>
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
import { ref, computed, watch, onMounted, onUnmounted } from 'vue';
import { useDropZone } from '@vueuse/core';
import { usePlanStore } from '@/stores/plan';

// 시작일과 종료일을 저장할 변수
const startDate = ref('');
const endDate = ref('');
// 날짜 리스트를 저장할 배열
const dateList = ref([]);

// 시작일과 종료일이 유효한 범위인지 확인하는 computed 속성
const isRangeValid = computed(() => {
  // 시작일과 종료일이 모두 선택되었고, 시작일이 종료일보다 이전인지 확인
  return startDate.value && endDate.value && startDate.value <= endDate.value;
});

// 선택된 범위의 모든 날짜를 리스트에 추가하는 함수
const addRangeToList = () => {
  if (isRangeValid.value) {
    const start = new Date(startDate.value);
    const end = new Date(endDate.value);
    const daysToAdd = [];
    for (let date = start; date <= end; date.setDate(date.getDate() + 1)) {
      const isoDate = date.toISOString().split('T')[0];
      if (!dateList.value.includes(isoDate)) {
        // 날짜가 리스트에 없으면 추가
        daysToAdd.push(isoDate);
      }
    }
    dateList.value = dateList.value.concat(daysToAdd).sort(); // 날짜를 추가하고 정렬
  } else {
    alert('날짜의 범위가 맞지 않습니다. 확인 후 다시 입력해주세요!');
  }
};

// 리스트를 모두 초기화
const clearList = () => {
  dateList.value = [];
};

// 드래그 앤 드롭 이벤트 처리
const onDragStart = place => {
  event.dataTransfer.setData('place', JSON.stringify(place));
};

const onDrop = (event, date) => {
  event.preventDefault();
  const place = JSON.parse(event.dataTransfer.getData('place'));
  console.log('Dropped place:', place);
  // 드롭되었을때 장소 데이터에 날짜 넣기
};

// 즐겨찾기 항목에서 담기
const planStore = usePlanStore();
const placeList = ref([]);

// 컴포넌트가 마운트될 때 이벤트 리스너 추가
onMounted(() => {
  const favoritePlaces = document.querySelectorAll('.favorite-place');
  favoritePlaces.forEach(place => {
    place.addEventListener('dragstart', () => onDragStart(place));
  });
});

// 컴포넌트가 언마운트될 때 이벤트 리스너 제거
onUnmounted(() => {
  const favoritePlaces = document.querySelectorAll('.favorite-place');
  favoritePlaces.forEach(place => {
    place.removeEventListener('dragstart', () => onDragStart(place));
  });
});
</script>

<style scoped></style>
