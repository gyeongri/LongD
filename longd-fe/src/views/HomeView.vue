<template>
  <div>
    <label class="btn btn-primary" for="img">메인 사진 변경</label>
    <input type="file" id="img" autocomplete="img" @change="changImg" hidden />
  </div>
  <div class="box">
    <div class="overlap">
      <div class="view">
        <!-- 여기는 배경 사진들어가는 곳 -->
        <div
          class="overlap-group"
          :style="{ backgroundImage: `url(${backGroundImg})` }"
        >
          <!-- 프로필 -->
          <div class="group-2">
            <RouterLink :to="{ name: 'Profile' }"
              ><img
                class="myProfile rounded-full"
                alt="내 프로필"
                :src="myprofile.profilePicture"
            /></RouterLink>
            <div class="image">
              <img
                class="heart-suit"
                alt="Heart suit"
                src="/static/img/heart-suit.png"
              />
            </div>
            <RouterLink :to="{ name: 'PartnerInfo' }">
              <img
                class="partnerProfile rounded-full"
                alt="상대 프로필"
                :src="partnerInfo.profilePicture"
            /></RouterLink>
          </div>
        </div>
      </div>
      <!-- 디데이 -->
      <div class="overlap-wrapper">
        <div class="div-wrapper">
          <div class="text-wrapper-3">D+{{ coupleDday }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, watchEffect } from 'vue';
import { loginstate, partnerinfo, coupleDataGet } from '@/utils/api/user';
import { uploadImage } from '@/utils/api/photo';
import { useRouter } from 'vue-router';
import { useMainDisplayStore } from '@/stores/maindisplay.js';
import dayjs from 'dayjs';

const router = useRouter();
const myprofile = ref({});
const partnerInfo = ref({});
const coupleInfo = ref({});
const mainDisplayStore = useMainDisplayStore();

const today = ref(dayjs());
const startDay = ref();
const coupleDday = ref();
const backGroundImg = ref('/static/img/couple.jpg');
const changImg = event => {
  const formData = new FormData();
  formData.append('file', event.target.files[0]);
  uploadImage(
    formData,
    success => {
      backGroundImg.value = success.data;
    },
    error => {
      console.log('사진을 변환할 수 없어요.', error);
    },
  );
};

onMounted(() => {
  loginstate(
    success => {
      if (success.data === '롱디에 로그인 되어 있지 않음') {
        //     홈 실행시 로그인 여부를 체크해서 안되있으면 로그인 화면으로 팅궈냅니다
        //     '롱디에 로그인 되어 있지 않음' <<< 요거 문구 수정하면안됩니다 문구에 반응하는거임
        console.log('로그인 안되어있다.');
        mainDisplayStore.logOutPage = true;
        // 로그아웃 되어)
        router.push({ name: 'Login' });
      } else {
        console.log('롱디에 로그인 되어있다', success.data);
        myprofile.value = success.data;
      }
    },
    error => {
      console.log('error') + error;
    },
  );
  partnerinfo(
    data => {
      partnerInfo.value = data.data;
    },
    error => {
      console.log('Partner Info 가져오기 안됨', error);
    },
  );
  coupleDataGet(
    data => {
      coupleInfo.value = data.data;
      console.log(coupleInfo.value.startDay);
      startDay.value = dayjs(coupleInfo.value?.startDay);
      console.log(startDay.value);
    },
    error => {
      console.log('Couple Info 가져오기 안됨', error);
    },
  );
});

watchEffect(() => {
  coupleDday.value = today.value.diff(startDay.value, 'day');
});
</script>

<style scoped>
.box {
  height: 740px;
  width: 612px;
}

.box .view {
  height: 720px;
  left: 50px;
  position: absolute;
  top: 200px;
  width: 612px;
}

.box .overlap-group {
  background-size: 100%;
  background-repeat: no-repeat;
  height: 100%;
  width: 100%;
  left: -4px;
  position: relative;
}

.box .group-2 {
  height: 80px;
  left: 210px;
  position: absolute;
  top: 70px;
  width: 216px;
}

.box .partnerProfile {
  height: 80px;
  left: 136px;
  position: absolute;
  top: 0;
  width: 80px;
}

.image .heart-suit {
  height: 43px;
  left: 85px;
  object-fit: cover;
  position: relative;
  top: 20px;
  width: 42px;
}
.box .myProfile {
  height: 80px;
  left: 0;
  position: absolute;
  top: 0;
  width: 80px;
}

.box .overlap-wrapper {
  height: 78px;
  left: 230px;
  position: absolute;
  top: 180px;
  width: 266px;
}

.box .div-wrapper {
  background-image: url(/static/img/group-19.png);
  background-size: 100% 100%;
  height: 78px;
  position: relative;
  width: 264px;
}

.box .text-wrapper-3 {
  color: #ffffff;
  font-family: 'Jura-SemiBold', Helvetica;
  font-size: 50px;
  font-weight: 600;
  left: 39px;
  letter-spacing: 0;
  line-height: normal;
  position: absolute;
  text-align: center;
  text-shadow: 0px 4px 4px #00000040;
  top: 11px;
  white-space: nowrap;
  width: 182px;
}
</style>
