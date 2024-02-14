<template>
  <div class="flex-col">
    <div class="flex justify-end">
      <AppDropdown>
        <template v-slot>
          <li class="font-bold lis">
            <div>
              <label for="img">메인 사진 변경</label>
              <input
                type="file"
                id="img"
                autocomplete="img"
                @change="changImg"
                hidden
              />
            </div>
          </li>
          <li class="font-bold lis" @click="changeDday()">
            <a>처음 만난 날 변경</a>
          </li>
          <li class="font-bold lis" @click="unConnectLove()">
            <a>상대와 연결 끊기</a>
          </li>
          <li class="font-bold lis" @click="deleteMyData()">
            <a>회원 탈퇴</a>
          </li>
        </template>
      </AppDropdown>
    </div>
    <div class="">
      <!-- 백그라운드 이미지 -->
      <div class="flex justify-center">
        <figure class="relative">
          <div class="flex justify-center">
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
          </div>
        </figure>
      </div>
    </div>
  </div>
</template>

<script setup>
import AppDropdown from '@/components/app/AppDropdown.vue';
import { ref, onMounted, watchEffect } from 'vue';
import {
  partnerinfo,
  coupleDataGet,
  coupleDataModify,
  BrokenLove,
  DeleteUser,
} from '@/utils/api/user';
import { uploadImage } from '@/utils/api/photo';
import { useUserStore } from '@/stores/user.js';
import Swal from 'sweetalert2';
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

const changeDday = () => {
  coupleDataGet(
    success => {
      coupleInfo.value = success.data;
      console.log(coupleInfo.value);
    },
    async success2 => {
      const { value: date } = await Swal.fire({
        title: '여러분이 처음 만난 날을 입력해주세요.',
        input: 'date',
        didOpen: () => {},
        preConfirm: () => {
          const selectedDate = Swal.getInput().value;
          const today = new Date().toISOString().split('T')[0];
          console.log(selectedDate);
          if (!selectedDate) {
            Swal.showValidationMessage('날짜를 선택해주세요');
            return false;
          } else if (selectedDate > today) {
            Swal.showValidationMessage('만난일은 미래일 수 없습니다.');
            return false;
          }
        },
      });
      if (date) {
        await Swal.fire('우리가 처음 만난 날', date);
        coupleInfo.value.startDay = date;
        startDay.value = dayjs(date);
        await coupleDataModify(
          coupleInfo.value,
          success => {
            console.log(coupleInfo.value);
          },
          error => {
            Swal.fire('수정되지않았어요!', error);
            console.log('커플 정보가 수정되지 않음');
          },
        );
      } else {
        console.log(coupleInfo.value);
        Swal.fire('날짜가 입력되지 않았어요.');
      }
    },
    error => {
      console.log('커플리스트 정보 조회 불가능', error);
    },
  );
};

const unConnectLove = () => {
  Swal.fire({
    title:
      '상대와 연결을 끊으실건가요? 연결을 끊으면 우리의 모든 추억이 삭제됩니다.',
    showCancelButton: true,
    allowEscapeKey: false,
    confirmButtonText: '그래도 연결 끊기',
    cancelButtonText: '다시 생각해보기',
  }).then(result => {
    if (result.isConfirmed) {
      console.log('상대와 연결을 끊는다고 했다..');
      BrokenLove(
        success => {
          console.log('연결끊기 완료');
        },
        error => {
          console.log('연결끊기 실패', error);
        },
      );
    }
  });
};

const deleteMyData = () => {
  // 진짜 회원탈퇴할건지 물어보기
  Swal.fire({
    title:
      '회원탈퇴를 원하시나요? 회원탈퇴를 하면 롱디와의 모든 추억이 삭제됩니다.',
    showCancelButton: true,
    allowEscapeKey: false,
    confirmButtonText: '회원 탈퇴',
    cancelButtonText: '다시 생각해보기',
  }).then(result => {
    if (result.isConfirmed) {
      Swal.fire({
        title: '정말로 회원탈퇴하실건가요?',
        showCancelButton: true,
        allowEscapeKey: false,
        confirmButtonText: '변함없이 회원탈퇴',
        cancelButtonText: '다시 롱디와 함께하기',
      }).then(result => {
        if (result.isConfirmed) {
          console.log('진짜 회원탈퇴를 한다고 했다..');
          DeleteUser(
            success => {
              console.log('회원탈퇴 완료');
            },
            error => {
              console.log('회원탈퇴 실패', error);
            },
          );
        }
      });
    }
  });
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
        coupleInfo.value.coupleImgUrl =
          'https://longdssafy.s3.ap-northeast-2.amazonaws.com/ab0c912f-db17-4854-8ffd-a717207575e7frame.png';
      }
    },
    success => {
      console.log('커플매칭이 잘되었습니다!');
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

.lis {
  /* width: 10rem; */
}
</style>
