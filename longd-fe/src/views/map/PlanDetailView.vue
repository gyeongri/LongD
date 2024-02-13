<template>
<<<<<<< HEAD
=======
  <div>
    <!-- 마커를 표시할 지도 -->
    <div class="googleMap" id="googleMap"></div>
  </div>
>>>>>>> develop
  <div v-for="plan in planInfoDetail" :key="plan.id">
    {{ plan }}
  </div>
</template>

<script setup>
<<<<<<< HEAD
import { ref, onMounted, watchEffect } from 'vue';
=======
import { ref, onMounted, watchEffect, watch } from 'vue';
>>>>>>> develop
import { useRoute } from 'vue-router';
import { getPlanDetail } from '@/utils/api/plan';

const currentId = ref('');
<<<<<<< HEAD
const planInfoDetail = ref();
=======
const planInfoDetail = ref([]);
>>>>>>> develop

const router = useRoute();

const getCurrentRouteId = () => {
  currentId.value = router.params.id;
};
<<<<<<< HEAD

// 컴포넌트가 마운트될 때와 라우터의 변경을 감지하여 현재 ID를 업데이트합니다.
onMounted(() => {
  getCurrentRouteId;
=======
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

// 컴포넌트가 마운트될 때와 라우터의 변경을 감지하여 현재 ID를 업데이트합니다.
onMounted(async () => {
  await initMap();
  getCurrentRouteId();
>>>>>>> develop

  getPlanDetail(
    currentId.value,
    success => {
<<<<<<< HEAD
      console.log(success.data);
      planInfoDetail.value = success.data;
=======
      // console.log(typeof success.data);
      // console.log(success.data);
      success.data.forEach(element => {
        planInfoDetail.value.push(element);
      });
>>>>>>> develop
    },
    error => {
      console.log(error);
    },
  );
});
watchEffect(getCurrentRouteId);
</script>

<<<<<<< HEAD
<style scoped></style>
=======
<style scoped>
.googleMap {
  height: 600px;
  width: 750px;
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
</style>
>>>>>>> develop
