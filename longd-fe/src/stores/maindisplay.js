import { ref, computed } from 'vue';
import { defineStore } from 'pinia';

export const useMainDisplayStore = defineStore(
  'display',
  () => {
    const closedPage = ref(false);
    const isClosed = computed(() => closedPage.value);
    return { closedPage, isClosed };
  },
  { persist: true },
);
