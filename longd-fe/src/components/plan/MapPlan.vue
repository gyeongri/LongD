<template>
  <!-- 제목 입력 -->
  <div class="flex">
    <label class="flex items-center" for="title">제목: </label>
    <input
      class="input input-bordered input-sm flex-1"
      id="title"
      type="text"
      placeholder="일정 제목을 입력해주세요."
      v-model="planTitle"
    />
  </div>
  <!-- 일정 입력하기 -->
  <div class="flex flex-col">
    <div class="flex justify-between">
      <label for="start">시작 날짜:</label>
      <input id="start" type="date" v-model="startDay" />
    </div>
    <div class="flex justify-between">
      <label for="end">종료 날짜:</label>
      <input id="end" type="date" v-model="endDay" />
    </div>
  </div>
  <div class="flex justify-end mr-1">
    <button
      class="btn btn-sm mr-1"
      style="background-color: #ffeded"
      @click="addRangeToList"
    >
      일정 추가
    </button>
    <button
      class="btn btn-sm"
      style="background-color: #ffeded"
      @click="clearList"
    >
      일정 초기화
    </button>
  </div>

  <!-- 즐겨찾기 목록 -->
  <p>즐겨찾기 목록</p>
  <!-- 로컬에 저장할 수 있도록 하기 -->
  <div
    v-for="place in planStore?.hopeList"
    :key="place.place_id"
    draggable="true"
    @dragstart="onDragStart(place)"
  >
    <div class="stats shadow">
      <div class="stat">
        <div class="favorite-place">
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
      <div>
        여기에 원하는 장소를 넣어주세요.
        <div
          v-for="(place, placeIndex) in getPlacesForDate(date)"
          :key="placeIndex"
        >
          <div class="stats shadow">
            <div class="stat">
              <div class="favorite-place">
                <strong>{{ place.title }}</strong>
                <button @click="removePlace(date, placeIndex)">X</button>
              </div>
            </div>
          </div>
        </div>
        <!-- {{ placeList }} -->
      </div>
    </div>
  </div>

  <button @click="openModal()">저장</button>
</template>

<script setup>
import { ref, computed, watch, onMounted, onUnmounted } from 'vue';
import { useRouter } from 'vue-router';
import { usePlanStore } from '@/stores/plan';
import { postPlanAllData } from '@/utils/api/plan';
import Swal from 'sweetalert2';

const router = useRouter();

// 즐겨찾기용
const planStore = usePlanStore();

// 최종 백에 넘겨줄 것 다 담을 변수
const planAll = ref();

// 일정 제목(백에 넘겨줄 것)
const planTitle = ref();

// 시작일과 종료일을 저장할 변수(백에 넘겨줄 것)
const startDay = ref('');
const endDay = ref('');
// 날짜 리스트를 저장할 배열
const dateList = ref([]);

// 시작일과 종료일이 유효한 범위인지 확인
const isRangeValid = computed(() => {
  return startDay.value && endDay.value && startDay.value <= endDay.value;
});

// 선택된 범위의 모든 날짜를 리스트에 추가하는 함수
const addRangeToList = () => {
  if (isRangeValid.value) {
    const start = new Date(startDay.value);
    const end = new Date(endDay.value);
    const daysToAdd = [];
    for (let date = start; date <= end; date.setDate(date.getDate() + 1)) {
      const isoDate = date.toISOString().split('T')[0];
      if (!dateList.value.includes(isoDate)) {
        // 날짜가 리스트에 없으면 추가
        daysToAdd.push(isoDate);
      }
    }
    // 날짜를 추가하고 정렬
    dateList.value = dateList.value.concat(daysToAdd).sort();
  } else {
    Swal.fire('날짜의 범위가 맞지 않습니다. 확인 후 다시 입력해주세요!');
  }
};

// 리스트를 모두 초기화
const clearList = () => {
  dateList.value = [];
};

// 새로운 일정을 설정할 때 이전에 있는 날짜 데이터 중에서
// 새로운 일정 범위에 해당하지 않는 것들을 리스트에서 제거
const removeDatesOutsideRange = (start, end) => {
  dateList.value = dateList.value.filter(date => {
    if (date < start || date > end) {
      // 해당 날짜에 연결된 모든 장소를 제거
      const places = datePlaceMap.value[date];
      if (places) {
        delete datePlaceMap.value[date];
        placeList.value = placeList.value.filter(place => place.date !== date);
      }
      return false;
    }
    return true;
  });
};

// 시작일과 종료일이 변경될 때 새로운 일정 범위를 계산하여
// 해당 범위 이외의 날짜를 리스트에서 제거(위에 있는 removeDatesOutsideRange 함수 사용)
const handleDateRangeChange = () => {
  if (isRangeValid.value) {
    const start = new Date(startDay.value);
    const end = new Date(endDay.value);
    removeDatesOutsideRange(
      start.toISOString().split('T')[0],
      end.toISOString().split('T')[0],
    );
  }
};

// 시작일과 종료일이 변경될 때마다 새로운 일정 범위 변수 업데이트
watch([startDay, endDay], () => {
  handleDateRangeChange();
});

// 드래그 앤 드롭 이벤트 처리
const onDragStart = place => {
  event.dataTransfer.setData('application/json', JSON.stringify(place));
};

// 담긴 장소들 목록(백에 넘겨줄 것)
const placeList = ref([]);
// 각 날짜에 대한 장소 리스트를 저장할 객체
const datePlaceMap = ref({});

// 드롭된 장소를 각 날짜에 맞게 추가
const addPlaceToDate = (date, place) => {
  if (!datePlaceMap.value[date]) {
    datePlaceMap.value[date] = [];
  }
  place.myOrder = datePlaceMap.value[date].length + 1;
  datePlaceMap.value[date].push(place);
};

// 각 날짜에 해당하는 장소 리스트를 반환
const getPlacesForDate = date => {
  if (!datePlaceMap.value[date]) {
    return [];
  }
  return datePlaceMap.value[date].sort((a, b) => a.myOrder - b.myOrder);
};

const onDrop = (event, date) => {
  event.preventDefault();
  const place = JSON.parse(event.dataTransfer.getData('application/json'));
  // 드롭되었을때 장소 데이터에 날짜 넣기
  place.date = date;
  addPlaceToDate(date, place);
  // 수정된 place를 placeList에 추가
  placeList.value = [...placeList.value, place];
  console.log(place, date, placeList.value);
};

// 삭제버튼 눌렀을 때 제거
const removePlace = (date, placeIndex) => {
  // datePlaceMap에서 제거
  const places = datePlaceMap.value[date];
  if (places) {
    places.splice(placeIndex, 1);
  }
  // placeList에서 제거
  placeList.value.splice(placeIndex, 1);
  console.log(placeList.value);
};

// 모달 띄우기
const openModal = () => {
  Swal.fire({
    title:
      "일정 계획이 모두 끝나셨나요? 아래 '여행 일정 생성' 버튼을 누르시면 여행일정이 생성됩니다.",
    showCancelButton: true,
    allowEscapeKey: false,
    confirmButtonText: '여행 일정 생성',
    cancelButtonText: '계속 편집 하기',
  }).then(result => {
    if (result.isConfirmed) {
      if (
        planTitle.value &&
        startDay.value &&
        endDay.value &&
        placeList.value.length !== 0
      ) {
        console.log('sendPlan함수 실행!');
        sendPlan();
      } else {
        console.log(
          planTitle.value,
          startDay.value,
          endDay.value,
          placeList.value,
        );
        Swal.fire('입력되지 않은 정보가 있습니다. 다시 확인해주세요.');
      }
    } else {
      Swal.fire('일정 계획이 끝나면 꼭 저장 버튼을 눌러주세요!');
    }
  });
};

// 정보 보내기
const sendPlan = () => {
  console.log('sendPlan 함수 실행될 예정');
  planAll.value = {
    title: planTitle.value,
    dateStart: startDay.value,
    dateEnd: endDay.value,
    planInfo: placeList.value,
  };
  postPlanAllData(
    planAll.value,
    success => {
      console.log('여행일정이 등록되었습니다.', success);
      Swal.fire('저장되었습니다.');
      router.push({ name: 'PlanList' });
    },
    error => {
      console.log('여행 일정이 등록되지 않음', error);
    },
  );
  console.log(planAll.value);
};

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
