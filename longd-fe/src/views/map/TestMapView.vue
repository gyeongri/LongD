<template>
  <p>하트를 선택해주세요💕</p>
  <div class="rating gap-1">
    <input type="radio" name="rating-3" class="mask mask-heart bg-red-400" />
    <input type="radio" name="rating-3" class="mask mask-heart bg-orange-400" />
    <input
      type="radio"
      name="rating-3"
      class="mask mask-heart bg-yellow-400"
      checked
    />
    <input type="radio" name="rating-3" class="mask mask-heart bg-lime-400" />
    <input type="radio" name="rating-3" class="mask mask-heart bg-green-400" />
  </div>
  <div>
    여행일정 나오게하기
    <!-- 제목 입력 -->
    <label for="title">제목</label>
    <input id="title" type="text" v-model="planTitle" />
    <!-- 일정선택 -->
    <div>
      <label for="start">Start Date:</label>
      <input id="start" type="date" v-model="startDate" />
      <label for="end">End Date:</label>
      <input id="end" type="date" v-model="endDate" />
      <button class="btn" @click="addRangeToList">일정추가</button>
      <button class="btn-outline" @click="clearList">목록비우기</button>
      <!-- 여기 넣을 것들 만들어보기 -->
      <div class="flex flex-col items-center">
        <img ref="pngRef" src="/star.png" alt="Drag me" h-10 />
        <span>star</span>
      </div>
      <div class="flex flex-col items-center">
        <img src="/static/img/heart-suit.png" alt="Drag me" h-10 />
        <span>heart</span>
      </div>
      <!-- 날짜별 드롭하는 곳 만들기 -->
      <ul>
        <li v-for="(date, index) in dateList" :key="index">
          {{ date }}
          <!-- 여기에 드롭인 넣기 -->
          <div grid="~ cols-2 gap-2">
            <div ref="dropZoneRef">
              <div font-bold mb2>여기로 이동해주세요.</div>
              <div>
                <BooleanDisplay :value="isOverDropZone" />
              </div>
            </div>
          </div>
          <!-- 여기까지 -->
          <button @click="removeDate(index)">일정 삭제</button>
        </li>
      </ul>
    </div>
    <!--  -->
    <h2>제목 입력하기</h2>
    <h3>날짜지정하기 => 즐겨찾기 목록 아래에 달력 만들어지기</h3>
    <p>즐겨찾기 목록 보이기</p>
    <p>달력 만들어지면 즐겨찾기 목록에서 달력으로 가지고 오기</p>
    <p>데이터 담기고 나면 저장해서 여행목록 리스트로 보내기</p>
  </div>
</template>

<script setup>
import { ref, computed, watch } from 'vue';
import { useDropZone, useEventListener } from '@vueuse/core';

// 전체
const planInfo = ref({});
// 제목
const planTitle = ref();
// 시작일과 종료일을 저장할 변수
const startDate = ref();
const endDate = ref();
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

// 여기는 드롭 관련
const eventList = ref([]);
// 각 날짜별 이벤트 담을 것

const onDrop = files => {
  if (files) {
    // 이미 리스트에 있는건지 확인하는 거
    const checkFile = files.some(newFile =>
      eventList.value.some(file => file.name === newFile.name),
    );
    if (!checkFile) {
      eventList.value = [
        ...eventList.value,
        ...files.map(file => ({
          name: file.name,
          // 여기에 넣을 속성들 입력하기
        })),
      ];
      // 여기에 이벤트 값에 날짜 할당해주는 것도 하기
      console.log(eventList.value);
    }
  }
};
const dropZoneRef = ref();
const testRef = ref();
const { isOverDropZone } = useDropZone(dropZoneRef, onDrop);
useEventListener(testRef, 'dragstart', event => {
  event.dataTransfer?.setData('image/png', 'heart-suit.png');
});

//  이거는 가져온 거 사용하기!

// dateList를 순회하면서 각 날짜를 키로 하는 빈 객체 생성
const dateEvents = computed(() => {
  const events = {};
  dateList.value.forEach(date => {
    events[date] = [];
  });
  return events;
});

// 새로운 일정을 dateEvents에 추가하는 함수
const addEventToDate = (date, event) => {
  dateEvents.value[date].push(event);
};

// 리스트에서 특정 날짜의 특정 인덱스의 일정을 제거하는 함수
const removeEvent = (date, eventIndex) => {
  dateEvents.value[date].splice(eventIndex, 1);
};
</script>

<style scoped></style>
