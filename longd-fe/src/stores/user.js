import { defineStore } from 'pinia';
import { computed, ref } from 'vue';
export const useUserStore = defineStore('user', () => {
  const userState = ref('');
  const setUserState = function (state) {
    userState.value = state;
  };
  const isLogin = computed(
<<<<<<< HEAD   (bd1350 [BE] 로그인 경로 오타수정)
<<<<<<< HEAD   (1df2c7 Merge branch 'develop' of https://i10d206.p.ssafy.io:8989/a/)
    () =>
      !(
        userState.value === '롱디에 로그인 되어 있지 않음' ||
        userState.value === '' ||
        userState.value === null
      ),
=======
    () => userState.value !== '롱디에 로그인 되어 있지 않음',
>>>>>>> CHANGE (610089 Merge branch 'develop' of https://i10d206.p.ssafy.io:8989/a/)
=======
    () =>
      !(
        userState.value === '롱디에 로그인 되어 있지 않음' ||
        userState.value === '' ||
        userState.value === null
      ),
>>>>>>> CHANGE (530ce4 Merge branch 'develop' of https://i10d206.p.ssafy.io:8989/a/)
  );
  return { userState, setUserState, isLogin };
});
