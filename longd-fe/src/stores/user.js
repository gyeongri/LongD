import { defineStore } from 'pinia';
import { ref } from 'vue';
export const useUserStore = defineStore('user', () => {
  const userState = ref('');
  const setUserState = function (state) {
    userState.value = state;
  };

  return { userState, setUserState };
});
