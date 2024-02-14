<template>
  <div class="flex gap-8">
    <div class="flex flex-col">
      <!-- 백그라운드 이미지 -->
      <figure class="relative">
        <img :src="coupleInfo.coupleImgUrl" alt="backgroundImage" />
        <div class="absolute top-0 left-1/2 transform -translate-x-1/2 m-4">
          <!-- 디데이 -->
          <div class="text-wrapper-3">D+{{ coupleDday }}</div>
          <!-- 프로필 부분 -->
          <div class="flex justify-center items-center">
            <RouterLink :to="{ name: 'Profile' }"
              ><img
                class="myProfile rounded-full h-[6rem] w-[6rem]"
                alt="내 프로필"
                :src="userStore?.getUserState?.profilePicture"
            /></RouterLink>
            <img
              class="heart-suit h-[4rem] w-[4rem]"
              alt="Heart suit"
              src="/static/img/heart-suit.png"
            />
            <RouterLink :to="{ name: 'PartnerInfo' }">
              <img
                class="partnerProfile rounded-full h-[6rem] w-[6rem]"
                alt="상대 프로필"
                :src="partnerInfo?.profilePicture"
            /></RouterLink>
          </div>
        </div>
      </figure>
    </div>
    <div>
      <label class="btn btn-secondary-content" for="img">메인 사진 변경</label>
      <input
        type="file"
        id="img"
        autocomplete="img"
        @change="changImg"
        hidden
      />
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
      coupleInfo.value.coupleImgUrl = success.data[0]['pathUrl'];
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
    success2 => {
      console.log('사진을 변환했습니다.', success2);
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
.text-wrapper-3 {
  background-image: url(/static/img/group-19.png);
  background-size: 100% 100%;
  color: #ffffff;
  font-family: 'Jura-SemiBold', Helvetica;
  font-size: 50px;
  font-weight: 600;
  letter-spacing: 0;
  line-height: normal;
  text-align: center;
  text-shadow: 0px 4px 4px #00000040;
  white-space: nowrap;
  width: 100%;
}

.toptape {
  height: 100%; /* 부모 요소의 높이를 100%로 설정 */
  display: flex; /* Flexbox 레이아웃 사용 */
  justify-content: center; /* 가로 방향 가운데 정렬 */
  align-items: center; /* 세로 방향 가운데 정렬 */
}

.tape {
  height: 50%;
}
</style>
