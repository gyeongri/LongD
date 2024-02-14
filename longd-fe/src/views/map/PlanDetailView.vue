<template>
<<<<<<< HEAD
=======
  <div class="flex justify-end gap-1">
    <button
      class="btn btn-sm"
      style="background-color: #ffeded"
      @click="deletePlan()"
    >
      삭제
    </button>
    <button
      class="btn btn-sm"
      style="background-color: #ffeded"
      @click="goList"
    >
      목록
    </button>
  </div>
>>>>>>> develop
  <div class="box">
    <div class="box">
      <div>제목</div>
      <div>{{ planDetail.title }}</div>
    </div>
    <div class="box">
      <div>일정</div>
      <div>{{ planDetail.dateStart }}~{{ planDetail.dateEnd }}</div>
    </div>
    <div></div>
  </div>
  <div class="box">
    <!-- 마커를 표시할 지도 -->
    <div class="googleMap" id="googleMap"></div>
    <div v-for="date in dateList" :key="date.id">
      <div>
        <div>
          {{ date }}
          <div v-for="item in getItemsByDate(date)" :key="item.id">
            <button>{{ item.title }}</button>
          </div>
        </div>

        <br />
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, watchEffect, watch } from 'vue';
<<<<<<< HEAD
import { useRoute } from 'vue-router';
import { getPlanDetail, getPlan } from '@/utils/api/plan';
const planDetail = ref('');
const currentId = ref('');
const planInfoDetail = ref([]);
const dateList = ref([]);
const router = useRoute();
=======
import { useRouter, useRoute } from 'vue-router';
import {
  getPlanDetail,
  getPlan,
  deletePlanData,
  getGalleryWithPlanID,
} from '@/utils/api/plan';
import Swal from 'sweetalert2';
const route = useRoute();
const planDetail = ref('');
const planGalleryList = ref([]);
const currentId = ref('');
const planInfoDetail = ref([]);
const dateList = ref([]);
const router = useRouter();
>>>>>>> develop
const getItemsByDate = date => {
  return planInfoDetail.value.filter(item => item.date === date);
};
const getCurrentRouteId = () => {
  currentId.value = route.params.id;
};
const defaultCenter = { lat: 36.10680122096389, lng: 128.4178078082704 };

const map = ref(null);
const initMap = async () => {
  // 기본 좌표로 지도 생성
  map.value = new google.maps.Map(document.getElementById('googleMap'), {
    center: defaultCenter,
    zoom: 15,
  });
  // 여행 좌표에 마커 추가
  const bounds = new google.maps.LatLngBounds();

  watch(planInfoDetail.value, (newvalue, oldvalue) => {
    newvalue.forEach(data => {
      if (map.value) {
        console.log(data.latitude);
      }
      const customMarkerImage =
        'https://longdssafy.s3.ap-northeast-2.amazonaws.com/87147053-0c9c-4059-8759-71252b89cde320240212_182456.jpg';

      const marker = new google.maps.Marker({
        position: { lat: data.latitude, lng: data.longitude },
        map: map.value,
        title: '되나?',
        icon: {
          url: customMarkerImage,
          scaledSize: new google.maps.Size(30, 30), // 그림의 크기 조절
        },
      });

      const location = new google.maps.LatLng(data.latitude, data.longitude);
      bounds.extend(location);
    });

    // 모든 마커를 추가한 후에 fitBounds 호출
    map.value.fitBounds(bounds);
  });
};
function generateDateList(startDate, endDate) {
  const dateList = [];
  let currentDate = new Date(startDate);

  while (currentDate <= new Date(endDate)) {
    dateList.push(currentDate.toISOString().split('T')[0]);
    currentDate.setDate(currentDate.getDate() + 1);
  }

  return dateList;
}

const deletePlan = function () {
  Swal.fire({
    title: '진짜 삭제하시겠습니까?',
    showCancelButton: true,
    confirmButtonText: '예',
    cancelButtonText: '아니오',
  }).then(result => {
    if (result.isConfirmed) {
      // 사용자가 '예'를 눌렀을 때의 로직
      deletePlanData(
        currentId.value,
        success => {
          console.log(currentId.value);
          router.push({ name: 'PlanList' });
          // 삭제 성공 시 추가적인 로직 작성
        },
        fail => {
          console.error(fail);
          // 삭제 실패 시 추가적인 로직 작성
        },
      );
    }
    // '아니오'를 눌렀을 때는 아무 로직도 추가하지 않음
  });
};
//리스트로 돌려보낼함수
const goList = function () {
  router.push({ name: 'PlanList' });
};
// 컴포넌트가 마운트될 때와 라우터의 변경을 감지하여 현재 ID를 업데이트합니다.
onMounted(async () => {
  await initMap();
  getCurrentRouteId();
  getPlanDetail(
    currentId.value,
    success => {
      // console.log(typeof success.data);
      // console.log(success.data);
      success.data.forEach(element => {
        planInfoDetail.value.push(element);
      });
    },
    error => {
      console.error(error);
    },
  );
  getPlan(currentId.value, success => {
    planDetail.value = success.data;
    dateList.value = generateDateList(
      success.data.dateStart,
      success.data.dateEnd,
    );
  });
  getGalleryWithPlanID(
    currentId.value,
    success => {
      planGalleryList.value = success.data;
    },
    error => {
      console.error(error);
    },
  );
  getPlan(currentId.value, success => {
    planDetail.value = success.data;
    dateList.value = generateDateList(
      success.data.dateStart,
      success.data.dateEnd,
    );
  });
});
watchEffect(getCurrentRouteId);
</script>

<style scoped>
.googleMap {
  height: 600px;
  width: 550px;
}

div[aria-hidden='true'] {
  display: none;
}

div[aria-hidden='false'] {
  display: block;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 1000;
}

div[aria-hidden='false'] > div {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #fff;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
}
.box {
  display: flex;
}
</style>
