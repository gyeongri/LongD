<template>
  <div class="box">
    <div class="group">
      <div class="overlap">
        <div class="view">
          <div class="overlap-group">
            <div class="overlap-group-wrapper">
              <div class="div">
                <div class="rectangle" />
                <div class="overlap-2">
                  <div class="rectangle-2" />
                  <div class="text-wrapper">S</div>
                </div>
                <div class="rectangle-3" />
                <div class="overlap-3">
                  <div class="rectangle-4" />
                  <div class="text-wrapper-2">M</div>
                </div>
                <div class="rectangle-5" />
                <div class="overlap-4">
                  <div class="rectangle-2" />
                  <div class="text-wrapper">T</div>
                </div>
                <div class="rectangle-6" />
                <div class="overlap-5">
                  <div class="rectangle-2" />
                  <div class="text-wrapper-2">T</div>
                </div>
                <div class="rectangle-7" />
                <div class="overlap-6">
                  <div class="rectangle-2" />
                  <div class="text-wrapper-2">F</div>
                </div>
                <div class="rectangle-8" />
                <div class="overlap-7">
                  <div class="rectangle-2" />
                  <div class="text-wrapper-2">S</div>
                </div>
                <div class="rectangle-9" />
                <div class="overlap-8">
                  <div class="rectangle-2" />
                  <div class="text-wrapper-2">W</div>
                </div>
              </div>
            </div>
            <div class="group-2">
              <RouterLink :to="{ name: 'Profile' }">
                <img
                  class="ellipse"
                  alt="Ellipse"
                  src="/static/img/ellipse-658.png"
              /></RouterLink>
              <img
                class="img"
                alt="Ellipse"
                src="/static/img/ellipse-659.png"
              />
              <div class="image">
                <img
                  class="heart-suit"
                  alt="Heart suit"
                  src="/static/img/heart-suit.png"
                />
              </div>
            </div>
          </div>
        </div>
        <div class="overlap-wrapper">
          <div class="div-wrapper">
            <div class="text-wrapper-3">D+365</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import { loginstate } from '@/utils/api/user';
import { useRouter } from 'vue-router';

const router = useRouter();

onMounted(() => {
  loginstate(
    success => {
      if (success.data === '롱디에 로그인 되어 있지 않음') {
        //     홈 실행시 로그인 여부를 체크해서 안되있으면 로그인 화면으로 팅궈냅니다
        //     '롱디에 로그인 되어 있지 않음' <<< 요거 문구 수정하면안됩니다 문구에 반응하는거임
        console.log('로그인 안되어있다.');
        router.push({ name: 'Login' });
      } else {
        console.log('로그인 되어있다', success.data);
      }
    },
    error => {
      console.log('error') + error;
    },
  );
});
</script>

<style scoped>
.box {
  height: 740px;
  width: 612px;
}

/* .box .group {
  height: 740px;
  left: 0;
  position: fixed;
  top: 0;
  width: 612px;
} */

/* .box .overlap {
  height: 740px;
  position: relative;
} */

.box .view {
  height: 720px;
  left: 50px;
  position: absolute;
  top: 200px;
  width: 612px;
}

.box .overlap-group {
  background-image: url(/static/img/frame.png);
  background-size: 100% 100%;
  height: 728px;
  left: -4px;
  position: relative;
  width: 620px;
}

.box .overlap-group-wrapper {
  height: 94px;
  left: 33px;
  position: absolute;
  top: 607px;
  width: 568px;
}

.box .div {
  -webkit-backdrop-filter: blur(31.21px) brightness(100%);
  backdrop-filter: blur(31.21px) brightness(100%);
  background-color: #ffffff8a;
  border: 0.78px solid;
  border-color: #ffffff;
  height: 94px;
  position: relative;
  width: 554px;
}

.box .rectangle {
  background-color: #ffffff;
  height: 57px;
  left: 4px;
  position: absolute;
  top: 31px;
  width: 75px;
}

.box .overlap-2 {
  height: 24px;
  left: 5px;
  position: absolute;
  top: 4px;
  width: 75px;
}

.box .rectangle-2 {
  background-color: #ebebeb;
  height: 23px;
  left: 0;
  position: absolute;
  top: 0;
  width: 75px;
}

.box .text-wrapper {
  color: #000000;
  font-family: 'K2D-Bold', Helvetica;
  font-size: 15px;
  font-weight: 700;
  left: 26px;
  letter-spacing: 0;
  line-height: normal;
  position: absolute;
  text-align: center;
  top: 2px;
  width: 25px;
}

.box .rectangle-3 {
  background-color: #ffffff;
  height: 57px;
  left: 83px;
  position: absolute;
  top: 31px;
  width: 74px;
}

.box .overlap-3 {
  height: 24px;
  left: 84px;
  position: absolute;
  top: 4px;
  width: 74px;
}

.box .rectangle-4 {
  background-color: #ebebeb;
  height: 23px;
  left: 0;
  position: absolute;
  top: 0;
  width: 74px;
}

.box .text-wrapper-2 {
  color: #000000;
  font-family: 'K2D-Bold', Helvetica;
  font-size: 15px;
  font-weight: 700;
  left: 25px;
  letter-spacing: 0;
  line-height: normal;
  position: absolute;
  text-align: center;
  top: 2px;
  width: 25px;
}

.box .rectangle-5 {
  background-color: #ffffff;
  height: 57px;
  left: 160px;
  position: absolute;
  top: 31px;
  width: 75px;
}

.box .overlap-4 {
  height: 24px;
  left: 161px;
  position: absolute;
  top: 4px;
  width: 75px;
}

.box .rectangle-6 {
  background-color: #ffffff;
  height: 57px;
  left: 317px;
  position: absolute;
  top: 31px;
  width: 75px;
}

.box .overlap-5 {
  height: 24px;
  left: 318px;
  position: absolute;
  top: 4px;
  width: 75px;
}

.box .rectangle-7 {
  background-color: #ffffff;
  height: 57px;
  left: 395px;
  position: absolute;
  top: 31px;
  width: 75px;
}

.box .overlap-6 {
  height: 24px;
  left: 396px;
  position: absolute;
  top: 4px;
  width: 75px;
}

.box .rectangle-8 {
  background-color: #ffffff;
  height: 57px;
  left: 473px;
  position: absolute;
  top: 31px;
  width: 75px;
}

.box .overlap-7 {
  height: 24px;
  left: 474px;
  position: absolute;
  top: 4px;
  width: 75px;
}

.box .rectangle-9 {
  background-color: #ffffff;
  height: 57px;
  left: 239px;
  position: absolute;
  top: 31px;
  width: 75px;
}

.box .overlap-8 {
  height: 24px;
  left: 240px;
  position: absolute;
  top: 4px;
  width: 75px;
}

.box .group-2 {
  height: 80px;
  left: 210px;
  position: absolute;
  top: 70px;
  width: 216px;
}

.box .ellipse {
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
.box .img {
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
