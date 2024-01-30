<template>
  <div class="desktop">
    <div class="div">
      <div class="long-d">
        여러분의 장거리 연애 도우미
        <br />
        Long-:D
      </div>
      <div class="text-wrapper">연결 CODE</div>
      <div class="text-wrapper-2">연결할래요</div>
      <div class="text-wrapper-3">CE829SAD29</div>
      <div class="overlap-group">
        <div class="text-wrapper-4">코드를 입력하세요</div>
        <img class="vector" alt="Vector" src="vector-353.svg" />
      </div>
      <Component
        class="component-1"
        corner="circle"
        divClassName="component-instance"
        text="제출"
        type="seconday"
      />
      <img class="ph-copy-thin" alt="Ph copy thin" src="ph-copy-thin.svg" />
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
.desktop {
  background-color: #ffffff;
  display: flex;
  flex-direction: row;
  justify-content: center;
  width: 100%;
}

.desktop .div {
  background-color: #ffffff;
  height: 1024px;
  position: relative;
  width: 1440px;
}

.desktop .long-d {
  color: var(--x-7-xu-6-0k);
  font-family: 'Inter-ExtraLight', Helvetica;
  font-size: 64px;
  font-weight: 200;
  left: 214px;
  letter-spacing: 0;
  line-height: normal;
  position: absolute;
  text-align: center;
  top: 250px;
  width: 1012px;
}

.desktop .text-wrapper {
  color: #3b3b3b;
  font-family: 'Inika-Regular', Helvetica;
  font-size: 30px;
  font-weight: 400;
  left: 296px;
  letter-spacing: 0;
  line-height: normal;
  position: absolute;
  text-align: center;
  top: 587px;
  width: 230px;
}

.desktop .text-wrapper-2 {
  color: #3b3b3b;
  font-family: 'Inika-Regular', Helvetica;
  font-size: 30px;
  font-weight: 400;
  left: 296px;
  letter-spacing: 0;
  line-height: normal;
  position: absolute;
  text-align: center;
  top: 672px;
  width: 230px;
}

.desktop .text-wrapper-3 {
  color: #000000;
  font-family: 'Inter-ExtraLight', Helvetica;
  font-size: 30px;
  font-weight: 200;
  left: 609px;
  letter-spacing: 0;
  line-height: normal;
  position: absolute;
  text-align: center;
  top: 588px;
  width: 264px;
}

.desktop .overlap-group {
  height: 60px;
  left: 547px;
  position: absolute;
  top: 662px;
  width: 388px;
}

.desktop .text-wrapper-4 {
  color: #0000004c;
  font-family: 'Inter-ExtraLight', Helvetica;
  font-size: 30px;
  font-weight: 200;
  left: 62px;
  letter-spacing: 0;
  line-height: normal;
  position: absolute;
  text-align: center;
  top: 0;
  width: 264px;
}

.desktop .vector {
  height: 9px;
  left: 0;
  position: absolute;
  top: 48px;
  width: 388px;
}

.desktop .component-1 {
  border-color: #d9d9d9 !important;
  display: flex !important;
  height: 48px !important;
  left: 991px !important;
  position: absolute !important;
  top: 673px !important;
  width: 110px !important;
}

.desktop .component-instance {
  margin-bottom: -2.5px !important;
  margin-left: -19.5px !important;
  margin-right: -19.5px !important;
  margin-top: -8.5px !important;
}

.desktop .ph-copy-thin {
  height: 70px;
  left: 1008px;
  position: absolute;
  top: 573px;
  width: 55px;
}
</style>
