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
  <p>하트를 선택해주세요💕</p>
  <div class="rating gap-1">
  <input type="radio" name="rating-3" class="mask mask-heart bg-red-400" />
  <input type="radio" name="rating-3" class="mask mask-heart bg-orange-400" />
  <input type="radio" name="rating-3" class="mask mask-heart bg-yellow-400" checked />
  <input type="radio" name="rating-3" class="mask mask-heart bg-lime-400" />
  <input type="radio" name="rating-3" class="mask mask-heart bg-green-400" />
</div>
  <div class="flex flex-col gap-2">
    <div class="w-full h-auto relative">
      <p>드롭 더 비트</p>
      <div class="flex gap-6">
        <div class="flex flex-col items-center">
          
          <img ref="pngRef" src="/star.png" alt="Drag me" h-10>
          <span>star</span>
        </div>
        <div class="flex flex-col items-center">
          <img src="/static/img/heart-suit.png" alt="Drag me" h-10>
          <span>heart</span>
        </div>
      </div>

      <div grid="~ cols-2 gap-2">
        <div
          ref="dropZoneRef"
          class="flex flex-col w-full min-h-200px h-auto bg-gray-400/10 justify-center items-center mt-6 rounded"
        >
          <div font-bold mb2>
            여기로 이동해주세요.
          </div>
          <div>
            <BooleanDisplay :value="isOverDropZone" />
          </div>
          <div class="flex flex-wrap justify-center items-center">
            <div v-for="(file, index) in filesData" :key="index" class="w-200px bg-black-200/10 ma-2 pa-6">
              <p>Name: {{ file.name }}</p>
              <p>Size: {{ file.size }}</p>
              <p>Type: {{ file.type }}</p>
              <p>Last modified: {{ file.lastModified }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, watch, watchEffect } from 'vue'
import { useDropZone, useEventListener } from '@vueuse/core'

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
    removeDatesOutsideRange(start.toISOString().split('T')[0], end.toISOString().split('T')[0]);
  }
};

// 시작일과 종료일이 변경될 때마다 새로운 일정 범위를 처리합니다.
watch([startDate, endDate], () => {
  handleDateRangeChange();
});

// 여기까지

const filesData = ref<{ name: string, size: number, type: string, lastModified: number }[]>([])

function onDrop(files: File[] | null) {
  if (files) {
    const checkFile = files.some(newFile => filesData.value.some(file => file.name === newFile.name));
      if (!checkFile) {
    filesData.value = [
    ...filesData.value,  
    ...files.map(file => ({
      name: file.name,
      size: file.size,
      type: file.type,
      lastModified: file.lastModified,
    }))]
    console.log(filesData.value)
  }}
}

const dropZoneRef = ref<HTMLElement>()
const pngRef = ref()

const { isOverDropZone } = useDropZone(dropZoneRef, onDrop)

useEventListener(pngRef, 'dragstart', (event) => {
  event.dataTransfer?.setData('image/png', 'heart-suit.png')
})

</script>

<style scoped>
img {
  height: 50px;
  width: 50px;
}
</style>