<template>
  <div>
    <p>원하는 장소 검색</p>
    <input type="text" v-model="where" />
  </div>
  <GoogleMap
    :api-key="googleApiKey"
    style="width: 100%; height: 500px"
    :center="center"
    :zoom="8"
    language="kor"
  >
    <MarkerCluster>
      <Marker
        v-for="(location, i) in locations"
        :options="{ position: location }"
        :key="i"
      >
        <InfoWindow>
          <h1>{{ where }}</h1>
        </InfoWindow>
      </Marker>
    </MarkerCluster>
  </GoogleMap>
</template>

<script setup>
import { ref } from 'vue';
import { GoogleMap, Marker, MarkerCluster, InfoWindow } from 'vue3-google-map';
import { useDropZone } from '@vueuse/core';

const where = ref('');
const googleApiKey = ref(import.meta.env.VITE_GOOGLE_REST_API_KEY);
const center = { lat: 36.8971999, lng: 127.5349361 };
const locations = [
  { lat: 36.10684456115392, lng: 128.41835497890136 },
  { lat: 37.555899571774724, lng: 127.00524613483742 },
];
</script>

<style scoped>
input {
  border: 1px black solid;
}
</style>
<!-- <template>
  <div id="map"></div>
</template>

<script setup>
import { ref, onMounted } from 'vue';

const initMap = () => {
  const map = new google.maps.Map(document.getElementById("map"), {
    center: { lat: 37.5400456, lng: 126.9921017 },
    zoom: 10
  });

  const malls = [
    { label: "C", name: "코엑스몰", lat: 37.5115557, lng: 127.0595261 },
    { label: "G", name: "고투몰", lat: 37.5062379, lng: 127.0050378 },
    { label: "D", name: "동대문시장", lat: 37.566596, lng: 127.007702 },
    { label: "I", name: "IFC몰", lat: 37.5251644, lng: 126.9255491 },
    { label: "L", name: "롯데월드타워몰", lat: 37.5125585, lng: 127.1025353 },
    { label: "M", name: "명동지하상가", lat: 37.563692, lng: 126.9822107 },
    { label: "T", name: "타임스퀘어", lat: 37.5173108, lng: 126.9033793 }
  ];

  const bounds = new google.maps.LatLngBounds();
  const infoWindow = new google.maps.InfoWindow();

  malls.forEach(({ label, name, lat, lng }) => {
    const marker = new google.maps.Marker({
      position: { lat, lng },
      label,
      map
    });
    bounds.extend(marker.position);

    marker.addListener("click", () => {
      map.panTo(marker.position);
      infoWindow.setContent(name);
      infoWindow.open({
        anchor: marker,
        map
      });
    });
  });

  map.fitBounds(bounds);
};

onMounted(() => {
  // Ensure the Google Maps API is loaded before initializing the map
  if (typeof google !== 'undefined' && google.maps) {
    initMap();
  } else {
    // You might want to add some error handling if the API is not loaded
    console.error("Google Maps API not loaded.");
  }
});
</script>

<style>
#map {
  height: 500px;
}
</style> -->
