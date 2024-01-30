import { ref, computed } from 'vue';
import { defineStore } from 'pinia';

export const useDisplayStore = defineStore('display', () => {
  const closedPage = ref(false);
  const logOutPage = ref(false);
  return { closedPage, logOutPage};
});
