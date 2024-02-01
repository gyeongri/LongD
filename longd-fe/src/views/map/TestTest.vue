<!-- <template>
  <div>
    <p>원하는 장소 검색 : <input type="text" v-model="where" /></p>
    <button @click="search(where)">입력</button>
  </div>
  <div id="googleMap" style="height: 600px; width: 750px"></div>
</template>

<script setup>
import { onMounted, ref } from 'vue';

const defaultCenter = { lat: 36.8971999, lng: 127.5349361 };
// 센터(내위치기준)
const map = ref(null);
const where = ref('');
// const atmName = ref('');

// 1번(전체 흐름)
const initMap = async () => {
  let mapCenter;

  try {
    mapCenter = await getCurrentLocation();
  } catch (error) {
    console.error(error);
    mapCenter = defaultCenter;
  }

  map.value = new google.maps.Map(document.getElementById('googleMap'), {
    center: mapCenter,
    zoom: 15,
  });
  const userLocationMarker = new google.maps.Marker({
    position: mapCenter,
    map: map.value,
    title: 'Your location',
    icon: {
      url: '/star.png', // 현재 위치를 나타내는 아이콘 URL
      scaledSize: new google.maps.Size(80, 80),
    },
  });

  searchPlaces(mapCenter);

  google.maps.event.addListener(map.value, 'zoom_changed', () => {
    const center = map.value.getCenter();
    searchPlaces({ lat: center.lat(), lng: center.lng() });
  });
};

// 2번(1번 속 진행 순서)
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

        // marker.addListener('click', () => {
        //   atmName.value = result.name;
        // });
      });
    }
  });
};

// 3번(1번 속 진행순서)
const getCurrentLocation = async () => {
  if (!navigator.geolocation) {
    console.error('Geolocation is not supported by this browser.');
    throw new Error('Geolocation not supported');
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
      console.error('Unable to retrieve your location.');
      throw error;
    });
};

// 4번
onMounted(async () => {
  await initMap();
});
</script>

<style scoped></style> -->
