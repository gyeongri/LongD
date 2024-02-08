<template>
  <div>
  <label for="start">Start Date:</label>
  <input id="start" type="date" v-model="startDate">
  <label for="end">End Date:</label>
  <input id="end" type="date" v-model="endDate">
  <button @click="addRangeToList">Add Range</button>
  <button @click="clearList">Clear List</button>
  <ul>
    <li v-for="(date, index) in dateList" :key="index">
      {{ date }}
      <button @click="removeDate(index)">Remove</button>
    </li>
  </ul>
</div>
</template>

<script setup>
import { ref, computed } from 'vue'

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
      if (!dateList.value.includes(isoDate)) { // 날짜가 리스트에 없으면 추가
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
const removeDate = (index) => {
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
const saveDates = () => {
  startDate.value = startDate.value;
  endDate.value = endDate.value;
};
</script>

<style scoped></style>
