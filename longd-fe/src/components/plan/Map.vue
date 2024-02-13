<template>
  <div>
    <!-- 검색 입력창 -->
    <div class="join">
      <input
        class="input input-bordered join-item"
        type="text"
        v-model="where"
        placeholder="검색 후 Enter키를 눌러주세요."
        id="googleInput"
      />
    </div>
    <!-- 지도 표시 -->
    <div class="googleMap" id="googleMap"></div>

    <!-- 선택한 장소의 상세 정보 모달 -->
    <!-- <div v-if="selectedPlace">
      <h2>{{ selectedPlace.name }}</h2>
      <p>주소: {{ selectedPlace.address }}</p>
      <p>위도: {{ selectedPlace.latitude }}</p>
      <p>경도: {{ selectedPlace.longitude }}</p>

      <button @click="closeDetailModal">닫기</button>
    </div> -->

  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { usePlanStore } from '@/stores/plan.js';
const planStore = usePlanStore();
// 기본 좌표 설정
const defaultCenter = { lat: 36.10680122096389, lng: 128.4178078082704 };
const map = ref(null);
const where = ref('');
const searchResults = ref([]);
const selectedPlace = ref(null);

let infowindow = null; // 전역 변수로 infowindow 선언

// 초기 지도 설정 함수
const initMap = async () => {
  let mapCenter;

  // 현재 위치 가져오기 시도
  try {
    mapCenter = await getCurrentLocation();
  } catch (error) {
    console.error(error);
    // 현재 위치를 가져올 수 없으면 기본 좌표로 설정
    mapCenter = defaultCenter;
  }

  // 구글 지도 객체 생성
  map.value = new google.maps.Map(document.getElementById('googleMap'), {
    center: mapCenter,
    zoom: 15,
  });

  // 사용자 위치를 나타내는 마커 추가
  const userLocationMarker = new google.maps.Marker({
    position: mapCenter,
    map: map.value,
    title: 'Your location',
    icon: {
      url: '/star.png',
      scaledSize: new google.maps.Size(80, 80),
    },
  });

  // 검색 상자 초기화
  initSearchBox();
};

// 검색 상자 초기화 함수
const initSearchBox = () => {
  const input = document.getElementById('googleInput');
  const searchBox = new google.maps.places.SearchBox(input);
  input.style.width = '300px';
  input.style.height = '50px';

  // 지도의 검색 박스 위치 설정
  map.value.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

  // 지도의 bounds가 변경될 때마다 검색 상자의 bounds 업데이트
  map.value.addListener('bounds_changed', () => {
    searchBox.setBounds(map.value.getBounds());
  });

  // 검색 상자의 장소가 변경될 때마다 실행
  searchBox.addListener('places_changed', () => {
    const places = searchBox.getPlaces();

    if (places.length === 0) {
      return;
    }

    const bounds = new google.maps.LatLngBounds();
    searchResults.value = [];
    planStore.resetSearchResult();
    places.forEach(place => {
      if (place.geometry && place.geometry.location) {
        const marker = new google.maps.Marker({
          map: map.value,
          title: place.name,
          position: place.geometry.location,
        });

        bounds.extend(place.geometry.location);

        marker.addListener('click', () => {
          // 기존의 infowindow가 있으면 닫기
          if (infowindow) {
            infowindow.close();
          }

          // infowindow 생성 및 열기
          infowindow = new google.maps.InfoWindow({
            content: `<strong>${place.name}</strong><br>${place.formatted_address}<br><button id="detailButton">자세히 보기</button>`,
          });
          infowindow.open(map.value, marker);

          // infowindow의 DOM이 준비되면 버튼에 이벤트 추가
          infowindow.addListener('domready', () => {
            const detailButton = document.getElementById('detailButton');
            if (detailButton) {
              detailButton.addEventListener('click', () => {
                // 상세 정보 모달 열기
                showDetailModal(place);
              });
            }
          });

          // 마커의 위치를 지도의 중심으로 설정
          map.value.setCenter(marker.getPosition());

          // 선택한 장소 정보 설정
          selectedPlace.value = {
            name: place.name,
            address: place.formatted_address,
            latitude: place.geometry.location.lat(),
            longitude: place.geometry.location.lng(),
          };

          // 지도를 클릭된 마커 위치로 확대
          map.value.setZoom(18); // 원하는 확대 레벨로 조절
        });

        planStore.addSearchResult({
          id: place.place_id,
          name: place.name,
          address: place.formatted_address,
          geometry: place.geometry,
        });
        searchResults.value.push({
          id: place.place_id,
          name: place.name,
          address: place.formatted_address,
          geometry: place.geometry,
        });
      }
    });

    map.value.fitBounds(bounds);
  });
};

// 주변 장소 검색 함수
const searchPlaces = center => {
  const request = {
    location: center,
    radius: '3000',
    type: ['cafe'],
  };

  const service = new google.maps.places.PlacesService(map.value);
  service.nearbySearch(request, (results, status) => {
    if (status === google.maps.places.PlacesServiceStatus.OK && results) {
      results.forEach(result => {
        const marker = new google.maps.Marker({
          position: result.geometry.location,
          map: map.value,
          icon: {
            url: '/static/img/heart-suit.png',
            scaledSize: new google.maps.Size(50, 50),
          },
        });

        marker.addListener('click', () => {
          // 기존의 infowindow가 있으면 닫기
          if (infowindow) {
            infowindow.close();
          }

          // infowindow 생성 및 열기
          infowindow = new google.maps.InfoWindow({
            content: `<strong>${result.name}</strong><br>${result.vicinity}`,
          });
          infowindow.open(map.value, marker);

          map.value.setCenter(marker.getPosition());

          // 선택한 장소 정보 설정
          selectedPlace.value = {
            name: result.name,
            address: result.vicinity,
            latitude: result.geometry.location.lat(),
            longitude: result.geometry.location.lng(),
          };
          map.value.setZoom(18); // 원하는 확대 레벨로 조절
        });

        // 결과 창에 추가
        searchResults.value.push({
          id: result.id,
          name: result.name,
          address: result.vicinity,
        });
      });
    }
  });
};

// 현재 위치 가져오기 함수
const getCurrentLocation = async () => {
  if (!navigator.geolocation) {
    console.error('이 브라우저에서는 Geolocation이 지원되지 않습니다.');
    throw new Error('Geolocation이 지원되지 않습니다.');
  }

  return new Promise((resolve, reject) => {
    navigator.geolocation.getCurrentPosition(resolve, reject);
  })
    .then(position => {
      return {
        lat: position.coords.latitude,
        lng: position.coords.longitude,
      };
    })
    .catch(error => {
      console.error('현재 위치를 가져올 수 없습니다.');
      throw error;
    });
};

// 검색 결과 클릭 처리 함수
const handleResultClick = place => {
  console.log(place); // 확인을 위한 로그

  // 기존의 infowindow가 있으면 닫기
  if (infowindow) {
    infowindow.close();
  }

  // infowindow 생성 및 열기
  infowindow = new google.maps.InfoWindow({
    content: `<strong>${place.name}</strong><br>${place.formatted_address}<br><button id="detailButton">자세히 보기</button>`,
  });
  infowindow.open(map.value);

  // infowindow의 DOM이 준비되면 버튼에 이벤트 추가
  infowindow.addListener('domready', () => {
    const detailButton = document.getElementById('detailButton');
    if (detailButton) {
      detailButton.addEventListener('click', () => {
        // 상세 정보 모달 열기
        showDetailModal(place);
      });
    }
  });

  // 지도를 클릭된 위치로 확대
  map.value.setZoom(18); // 원하는 확대 레벨로 조절
  map.value.panTo(place.geometry.location);

  // 선택한 장소 정보 설정
  selectedPlace.value = {
    name: place.name,
    address: place.address,
    latitude: place.geometry.location.lat(),
    longitude: place.geometry.location.lng(),
  };
};

// 모달 닫기 함수
const closeDetailModal = () => {
  selectedPlace.value = null;
};

// 모달 열기 함수
const showDetailModal = place => {
  selectedPlace.value = place;
};

// 컴포넌트가 마운트되었을 때 실행
onMounted(async () => {
  await initMap();
});
</script>

<style scoped>
.googleMap {
  height: 600px;
  width: 750px;
}

#pac-input {
  width: 300px;
}

#results {
  margin-top: 20px;
  padding: 10px;
  border: 1px solid black;
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
