import { defineStore } from 'pinia';
import { ref } from 'vue';
export const usePlanStore = defineStore('plan', () => {
  const hopeList = ref([]);
  const data = ref({});

  const addHopeList = function (place) {
    data.value = {
      title: place.name,
      latitude: place.geometry.location.lat(),
      longitude: place.geometry.location.lng(),
    };
    hopeList.value.push(data.value);
  };

  return { hopeList, addHopeList };
});
