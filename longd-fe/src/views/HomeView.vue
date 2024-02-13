<template>
  <div class="main-side">
    <div class="left-side">
      <!-- 디데이 -->
      <div class="div-wrapper">
        <div class="text-wrapper-3">D+{{ coupleDday }}</div>
      </div>

      <div class="profiles">
        <RouterLink :to="{ name: 'Profile' }"
          ><img
            class="myProfile rounded-full"
            alt="내 프로필"
            :src="userStore?.getUserState?.profilePicture"
        /></RouterLink>
        <!-- <img
          class="heart-suit"
          alt="Heart suit"
          src="/static/img/heart-suit.png"
        /> -->
        <RouterLink :to="{ name: 'PartnerInfo' }">
          <img
            class="partnerProfile rounded-full"
            alt="상대 프로필"
            :src="partnerInfo?.profilePicture"
        /></RouterLink>
      </div>

      <div class="changebtn">
        <label class="btn btn-secondary-content" for="img"
          >메인 사진 변경</label
        >
        <input
          type="file"
          id="img"
          autocomplete="img"
          @change="changImg"
          hidden
        />
      </div>
    </div>
    <div class="right-side">
      <img :src="coupleInfo.coupleImgUrl" />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, watchEffect } from 'vue';
import { partnerinfo, coupleDataGet, coupleDataModify } from '@/utils/api/user';
import { uploadImage } from '@/utils/api/photo';
import { useUserStore } from '@/stores/user.js';
import dayjs from 'dayjs';

const partnerInfo = ref({});
const coupleInfo = ref({});
const userStore = useUserStore();

const today = ref(dayjs());
const startDay = ref();
const coupleDday = ref();

const changImg = event => {
  const formData = new FormData();
  formData.append('file', event.target.files[0]);
  uploadImage(
    formData,
    success => {
      coupleInfo.value.coupleImgUrl = success.data[0];
      coupleDataModify(
        coupleInfo.value,
        success => {
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
.main-side,
.changebtn {
  display: flex;
  justify-content: center;
  align-items: center;
}

.left-side {
  /* border: 3px solid black; */
  width: 40%;
  display: grid;
  justify-content: center;
  align-items: center;
}
.right-side {
  /* border: 3px solid black; */
  width: 60%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.profiles {
  display: flex;
  align-items: center; /* 수직 정렬을 중앙에 설정 */
  margin-bottom: 10vh;
}

.myProfile,
.partnerProfile {
  width: 30vh; /* 이미지의 너비를 원하는 크기로 설정 */
  height: 30vh; /* 이미지의 높이를 원하는 크기로 설정 */
  object-fit: cover; /* 이미지를 컨테이너에 맞추기 위해 사용 */
  margin-right: 10px; /* 이미지 사이에 간격을 조절 */
}

.text-wrapper-3 {
  background-image: url(/static/img/group-19.png);
  background-size: 100% 100%;
  position: relative;

  color: #ffffff;
  font-family: 'Jura-SemiBold', Helvetica;
  font-size: 50px;
  font-weight: 600;
  /* left: 39px; */
  letter-spacing: 0;
  line-height: normal;
  /* position: absolute; */
  text-align: center;
  text-shadow: 0px 4px 4px #00000040;
  bottom: 5vh;
  white-space: nowrap;
  width: 100%;
}
</style>
