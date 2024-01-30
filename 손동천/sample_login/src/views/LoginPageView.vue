<script setup>
import { checkloginstate, login, logout, test, userDelete } from "@/api/main";
import { useRouter } from "vue-router";
import { ref, onMounted } from "vue";

const { VITE_BASE_IP, VITE_VUE_API_URL } = import.meta.env;

const router = useRouter();

const login_state = ref("로그인 안되어있음");

onMounted(() => {
  checkloginstate(
    (data) => {
      login_state.value = data.data;
    },
    (error) => {
      console.log("onMounted 오류 : " + error);
    }
  );
});

const logoutbtn = () => {
  logout(
    (data) => {
      console.log(data.data, "logout");
    },
    (error) => {
      console.log(error);
    }
  );
  console.log("눌렀다 로그아웃번");
  router.push({ name: "home" });
};

const axiosLoginbtn = () => {
  login(
    (data) => {
      console.log(data.data, "wow");
    },
    (error) => {
      console.log(error);
    }
  );
  console.log("눌렀다 1번");
};
const testing = () => {
  test(
    (data) => {
      console.log(data.data, "test");
    },
    (error) => {
      console.log(error);
    }
  );
  console.log("눌렀다 5번");
};

const userinfobtn = () => {
  router.push({ name: "userinfo" });
};

const userDeletebtn = () => {
  userDelete(
    (data) => {
      alert("현재 회원 탈퇴 완료");
    },
    (error) => {
      console.log(error);
    }
  );
};
</script>

<template>
  <div class="main-side">
    <div class="left-side">
      <div class="part1-side">
        <a :href="VITE_VUE_API_URL + '/oauth2/authorization/naver'">
          <button @click="naverbtn">네이버 드가자</button>
        </a>
        <br />
        <a :href="VITE_VUE_API_URL + '/oauth2/authorization/kakao'">
          <button @click="kakaobtn">카카오 드가자</button> </a
        ><br />
        <a :href="VITE_VUE_API_URL + '/oauth2/authorization/google'">
          <button @click="googlebtn">구우글 드가자</button> </a
        ><br />

        <br />

        <p>현재 상태 : {{ login_state }}</p>

        <br />

        <button @click="axiosLoginbtn">Axios 너는 누구냐</button> <br />
        <button @click="testing">Axios 테스트 하자</button> <br />
        <button @click="userinfobtn">유저 정보 페이지로 넘어가는 버튼</button>
        <br />

        <button @click="logoutbtn">로그아웃 버튼</button>

        <br />
        <br />
        <button @click="userDeletebtn">회원 탈퇴</button>
      </div>

      <div class="part2-side">test</div>
    </div>

    <div class="right-side">test</div>
  </div>
</template>

<style>
.main-side {
  display: flex;
  justify-content: center;
}

.left-side {
  width: 50%;
}

.right-side {
  border: 1px solid;
  width: 50%;
}

.part2-side {
  border: 1px solid;
}
</style>
