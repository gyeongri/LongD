<template>
  {{ login_state }}
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
              <img
                class="ellipse"
                alt="Ellipse"
                src="/static/img/ellipse-658.png"
              />
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
//임시 내용입니다.
import { checkloginstate } from '@/api/user';
import { checkLogin } from '@/utils/api/user';
import { ref, onMounted } from 'vue';

const login_state = ref('로그인 안되어있음');

onMounted(() => {
  checkLogin(
    success => {
      console.log('loginCheck success' + success);
    },
    error => {
      console.log('loginCheck error' + error);
    },
  );

  checkloginstate(
    data => {
      login_state.value = data;
    },
    error => {
      console.log('onMounted 오류 : ' + error);
    },
  );
});
//임시 내용 끝
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
  left: 400px;
  object-fit: cover;
  position: fixed;
  top: 290px;
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

<!-- <template>
  <div>일단 이게 홈화면 대체</div>
</template>

<script setup></script>

<style scoped></style> -->
