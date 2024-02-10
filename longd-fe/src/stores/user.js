import { defineStore } from 'pinia';
import { computed, ref } from 'vue';
export const useUserStore = defineStore('user', () => {
  const userState = ref(null);

  const setUserState = function (state) {
    try {
      userState.value = state;
    } catch (error) {
      console.error(`여기에러`, error);
    }
  };

  const isLogin = computed(
    () =>
      !(
        userState.value === '롱디에 로그인 되어 있지 않음' ||
        userState.value === '' ||
        userState.value === null ||
        userState.value === false
      ),
  );
  return { userState, setUserState, isLogin };
});
