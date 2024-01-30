import { ref, computed } from 'vue';
import { defineStore } from 'pinia';

export const useUserStore = defineStore(
  'user',
  () => {
    // const API_URL = 'http://127.0.0.1:8000'
    const token = ref(null);
    const userPk = ref(null);
    const userName = ref(null);
    const loginUser = ref(null);
    const isSignUp = ref(false);
    // 회원가입 후 바로 로그인하기 위한 플래그 변수

    const isLogin = computed(() => {
      if (token.value === null) {
        userPk.value = null;
        userName.value = null;
        loginUser.value = null;
        return false;
      } else {
        return true;
      }
    });
    return { token, isLogin, userPk, userName, isSignUp, loginUser };
  },
  { persist: true },
);
