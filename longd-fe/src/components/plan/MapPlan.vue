<template>
  <!-- 제목 입력 -->
  <label for="title">제목</label>
  <input id="title" type="text" v-model="planTitle" />
  <!-- 일정 입력하기 -->
  <div>
    <label for="start">시작 날짜:</label>
    <input id="start" type="date" v-model="startDate" />
    <label for="end">종료 날짜:</label>
    <input id="end" type="date" v-model="endDate" />
    <button class="btn" @click="addRangeToList">일정 추가</button>
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
      <!-- BooleanDisplay 컴포넌트: 드롭 영역 위에 올려진 경우 표시됨 -->
      <!-- <div :class="{ displayed: isOverDropZone }"></div> -->
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
    여행일정 나열하기
    <h2>제목 입력하기</h2>
    <h3>날짜 선택하기 => 즐겨찾기 목록 아래에 달력 표시</h3>
    <p>즐겨찾기 목록 보이기</p>
    <p>달력이 표시되면 즐겨찾기 목록에서 달력으로 옮기기</p>
    <p>데이터 저장 후 여행 목록으로 보내기</p>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, onUnmounted } from 'vue';
import { useDropZone } from '@vueuse/core';
import { usePlanStore } from '@/stores/plan';

const planTitle = ref();

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

const placeList = ref([]);

const onDrop = (event, date) => {
  event.preventDefault();
  const place = JSON.parse(event.dataTransfer.getData('place'));
  // 드롭되었을때 장소 데이터에 날짜 넣기
  place.date = date;
  // 수정된 place를 placeList에 추가
  placeList.value = [...placeList.value, place];
  console.log('Dropped place:', place, date, placeList.value);
};

// 즐겨찾기 항목에서 담기
const planStore = usePlanStore();

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

<style scoped>
.displayed {
  border: solid black 1px;
}
</style>