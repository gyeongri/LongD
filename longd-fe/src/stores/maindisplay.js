import { ref, computed } from 'vue';
import { defineStore } from 'pinia';

export const useMainDisplayStore = defineStore(
  'display',
  () => {
    const closedPage = ref(false);
    const logOutPage = ref(false);
    const isVisible = computed(() => !logOutPage.value && !closedPage.value);
    return { closedPage, logOutPage, isVisible };
  },
  { persist: true },
);
