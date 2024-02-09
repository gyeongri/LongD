import { defineStore } from 'pinia';
import { computed, ref } from 'vue';
export const useUserStore = defineStore('user', () => {
  const userState = ref('');
  const setUserState = function (state) {
    userState.value = state;
  };
  const isLogin = computed(
    () => userState.value !== null && userState.value !== '',
  );
  return { userState, setUserState, isLogin };
});
