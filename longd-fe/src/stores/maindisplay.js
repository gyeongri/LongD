import { ref, computed } from 'vue';
import { defineStore } from 'pinia';

export const useMainDisplayStore = defineStore(
  'display',
  () => {
    const closedPage = ref(false);
    const logOutPage = ref(false);
<<<<<<< HEAD:longd-fe/src/stores/maindisplay.js
    const isVisible = computed(() => !logOutPage.value && !closedPage.value);
    return { closedPage, logOutPage, isVisible };
=======

    return { closedPage, logOutPage };
>>>>>>> geunryeol:전근렬/longd-fe/src/stores/maindisplay.js
  },
  { persist: true },
);
