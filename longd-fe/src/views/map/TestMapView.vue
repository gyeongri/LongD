<template>
  <div id="googleMap" style="height: 600px; width: 750px;"></div>
</template>

<script setup>
import { onMounted, ref } from 'vue';

const defaultCenter = { lat: 35.106187, lng: 129.042943 };
const map = ref(null);
const emit = defineEmits(['close'])
const atmName = ref('')

const close = ()=>{
  emit('close')
}

const initMap = async () => {
  let mapCenter;

  try {
    mapCenter = await getCurrentLocation();
  } catch (error) {
    console.error(error);
    mapCenter = defaultCenter;
  }

  map.value = new google.maps.Map(
    document.getElementById('googleMap'), {
      center: mapCenter,
      zoom: 15
    }
  );
  const userLocationMarker = new google.maps.Marker({
    position: mapCenter,
    map: map.value,
    title: 'Your location',
    icon: {
      url: '/star.png', // 현재 위치를 나타내는 아이콘 URL
      scaledSize: new google.maps.Size(80, 80)
    }
  });

  searchATMs(mapCenter);

  google.maps.event.addListener(map.value, 'zoom_changed', () => {
    const center = map.value.getCenter();
    searchATMs({ lat: center.lat(), lng: center.lng() });
  });
};

const searchATMs = (center) => {
  const request = {
    location: center,
    radius: '3000',
    type: ['cafe']
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
          }
        });

        marker.addListener('click', () => {
          atmName.value = result.name
        });
      });
    }
  });
};

const getCurrentLocation = async () => {
  if (!navigator.geolocation) {
    console.error("Geolocation is not supported by this browser.");
    throw new Error("Geolocation not supported");
  }

  return new Promise((resolve, reject) => {
    navigator.geolocation.getCurrentPosition(resolve, reject);
  }).then(position => {
    return {
      lat: position.coords.latitude,
      lng: position.coords.longitude
    };
  }).catch(error => {
    console.error("Unable to retrieve your location.");
    throw error;
  });
};

onMounted(async () => {
  await initMap();
});
</script>

<style scoped></style>