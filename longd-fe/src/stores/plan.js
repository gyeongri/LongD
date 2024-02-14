import { defineStore } from 'pinia';
import { ref } from 'vue';
export const usePlanStore = defineStore('plan', () => {
  const hopeList = ref([]);
  const data = ref({});
  const searchResults = ref([]);
  const addSearchResult = function (place) {
    searchResults.value.push(place);
  };
  const resetSearchResult = function () {
    searchResults.value = [];
  };
  const addHopeList = function (place) {
    console.log(place);
    data.value = {
      title: place.name,
      latitude: place.geometry.location.lat(),
      longitude: place.geometry.location.lng(),
    };
    hopeList.value.push(data.value);
  };
  const isHeartSelected = function (place) {
    // 해당 장소가 hopeList에 있는지 확인하여 선택 여부를 반환합니다.
    return hopeList.value.some(item => item.title === place.name);
  };

  return {
    hopeList,
    addHopeList,
    searchResults,
    addSearchResult,
    resetSearchResult,
    isHeartSelected,
  };
});
