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
        <div class="stat-value">{{ place.title }}</div>
      </div>
    </div>
  </div>
  <!-- 날짜별 드롭하는 곳 만들기 -->
  <div v-for="(date, index) in dateList" :key="index">
    {{ date }}
    <div
      ref="dropZone"
      class="flex flex-col w-full min-h-200px h-auto bg-gray-400/10 justify-center items-center mt-6 rounded"
    >
      <div>장소들 담는 공간</div>
      <BooleanDisplay :value="isOverDropZone" />
      <div class="flex flex-wrap justify-center items-center">
        <div
          v-for="(place, idx) in placeList"
          :key="idx"
          class="w-200px bg-black-200/10 ma-2 pa-6"
        >
          <p>{{ event.name }}</p>
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
import { ref, computed, watch } from 'vue';
import { useDropZone, useEventListener } from '@vueuse/core';
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

// 리스트에서 특정 인덱스의 날짜를 제거하는 함수
// 리스트 인덱스가 0이거나 마지막 인덱스인 경우에만 제거 허용
const removeDate = index => {
  if (index === 0 || index === dateList.value.length - 1) {
    dateList.value.splice(index, 1);
  } else {
    alert('중간 날짜는 삭제할 수 없습니다.');
  }
};

// 리스트를 모두 초기화하는 함수
const clearList = () => {
  dateList.value = [];
};

// 새로운 일정을 설정할 때 이전에 있는 날짜 데이터 중에서
// 새로운 일정 범위에 해당하지 않는 것들을 리스트에서 제거하는 함수
const removeDatesOutsideRange = (start, end) => {
  dateList.value = dateList.value.filter(date => date >= start && date <= end);
};

// 시작일과 종료일이 변경될 때 새로운 일정 범위를 계산하여
// 해당 범위 이외의 날짜를 리스트에서 제거하는 동작을 수행합니다.
const handleDateRangeChange = () => {
  if (isRangeValid.value) {
    const start = new Date(startDate.value);
    const end = new Date(endDate.value);
    removeDatesOutsideRange(
      start.toISOString().split('T')[0],
      end.toISOString().split('T')[0],
    );
  }
};

// 시작일과 종료일이 변경될 때마다 새로운 일정 범위를 처리합니다.
watch([startDate, endDate], () => {
  handleDateRangeChange();
});

// 즐겨찾기 항목에서 담기
const planStore = usePlanStore();
const planInfo = ref({});
const placeList = ref([]);
const dropZone = ref();
const favoritePlace = ref();
const onDrop = places => {
  if (places) {
    const checkPlace = places.some(newPlace =>
      placeList.value.some(place => place.name === newPlace.name),
    );
    if (!checkPlace) {
      placeList.value = [
        ...placeList.value,
        ...places.map(place => ({
          name: place.name,
          address: place.address,
          lat: place.lat,
          lng: place.lng,
        })),
      ];
      console.log(placeList.value);
    }
  }
};

const { isOverDropZone } = useDropZone(dropZone, onDrop);

useEventListener(favoritePlace, 'dragstart', event => {
  // 여기에 이벤트가 일어났을때 사용할 거 드래그하면 플레이스 정보에 날짜값 넣기!
  favoritePlace.value.date = event.date;
  console.log(event);
  console.log(event.data);
});
</script>

<style scoped></style>
