<template>
  <div>
    <label class="btn btn-primary" for="img">메인 사진 변경</label>
    <input type="file" id="img" autocomplete="img" @change="changImg" hidden />
  </div>

  <div class="box">
    <div class="overlap">
      <div class="view">
        <!-- 여기는 배경 사진들어가는 곳 -->
        <div class="rectangle" />
        <div class="imageMain">
          <img
            class="element"
            alt="메인 이미지"
            :src="coupleInfo.coupleImgUrl"
          />
        </div>
        <!-- <div
          class="overlap-group"
          :style="{ backgroundImage: `url(${coupleInfo.coupleImgUrl})` }"
        > -->
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
      <!-- </div> -->
      <!-- 디데이 -->
      <div class="overlap-wrapper">
        <div class="div-wrapper">
          <div class="text-wrapper">D+{{ coupleDday }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, watchEffect } from 'vue';
import {
  loginstate,
  partnerinfo,
  coupleDataGet,
  coupleDataModify,
} from '@/utils/api/user';
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

const changImg = event => {
  const formData = new FormData();
  formData.append('file', event.target.files[0]);
  uploadImage(
    formData,
    success => {
      coupleInfo.value.coupleImgUrl = success.data[0]['pathUrl'];
      console.log(coupleInfo.value);
      coupleDataModify(
        coupleInfo.value,
        success => {
          console.log(success);
          console.log('커플정보 보내기 완료!');
        },
        error => {
          console.log('커플정보 보내는 것에 실패했습니다.', error);
        },
      );
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
      startDay.value = dayjs(coupleInfo.value?.startDay);
      if (!coupleInfo.value.coupleImgUrl) {
        coupleInfo.value.coupleImgUrl = '/static/img/frame.png';
      }
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
/* .box {
  height: 740px;
  width: 612px;
} */

.box {
  height: 833px;
  left: 382px;
  position: absolute;
  top: 138px;
  width: 612px;
}

.box .overlap {
  height: 833px;
  position: relative;
}
/* 위 두개가 화면 위치 스타일 */

.box .view {
  height: 720px;
  /* left: 50px;
  position: absolute;
  top: 200px; */
  width: 612px;
}

.view .rectangle {
  background: linear-gradient(
    180deg,
    rgb(255, 255, 255) 0%,
    rgb(232.7, 232.7, 232.7) 100%
  );
  height: 720px;
  left: 0;
  position: absolute;
  top: 0;
  width: 612px;
}

/* 위 두개가 폴라로이드 배경 */

.imageMain {
  height: 557px;
  width: 554px;
}

.imageMain .element {
  height: 557px;
  left: 0;
  object-fit: cover;
  position: absolute;
  top: 0;
  width: 554px;
}

/* 위 두개는 메인 사진 폴라로이드 안에 들어가는 거 */
/* .box .overlap-group {
  background-size: 100%;
  background-repeat: no-repeat;
  height: 100%;
  width: 100%;
  left: -4px;
  position: relative;
} */

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

.box .text-wrapper {
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
