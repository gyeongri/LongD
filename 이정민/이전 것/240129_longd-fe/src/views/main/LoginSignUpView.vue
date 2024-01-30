<template>
  <div class="desktop">
    <div class="desktop-ver">
      <p class="div">
        <span class="text-wrapper">
          아직 회원이 아니시라면?
          <br />
        </span>
        <span class="span">회원가입하기</span>
      </p>
      <img class="line" alt="Line" src="line-1.svg" />
      <div class="long-d">
        여러분의 장거리 연애 도우미
        <br />
        Long-:D
      </div>

      <div class="group">
        <div class="overlap-group">
          <div class="group-wrapper">
            <div class="image-wrapper">
              <img class="image" alt="Image" src="image-4.png" />
            </div>
          </div>
          <img class="btng" alt="Btng" src="btng-1.png" />
          <div class="text-wrapper-2">Login</div>
        </div>
      </div>
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

.desktop .desktop-ver {
  background-color: #ffffff;
  height: 1024px;
  position: relative;
  width: 1440px;
}

.desktop .div {
  color: #3b3b3b9d;
  font-family: 'Inter-ExtraLight', Helvetica;
  font-size: 30px;
  font-weight: 200;
  left: 542px;
  letter-spacing: 0;
  line-height: normal;
  position: absolute;
  text-align: center;
  top: 768px;
  width: 318px;
}

.desktop .text-wrapper {
  color: #3b3b3b9d;
  font-family: 'Inter-ExtraLight', Helvetica;
  font-size: 30px;
  font-weight: 200;
  letter-spacing: 0;
}

.desktop .span {
  text-decoration: underline;
}

.desktop .line {
  height: 3px;
  left: 67px;
  object-fit: cover;
  position: absolute;
  top: 720px;
  width: 1291px;
}

.desktop .long-d {
  color: var(--b-mz-1g-1);
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

.desktop .group {
  height: 157px;
  left: 492px;
  position: absolute;
  top: 518px;
  width: 421px;
}

.desktop .overlap-group {
  height: 157px;
  position: relative;
  width: 419px;
}

.desktop .group-wrapper {
  height: 128px;
  left: 0;
  position: absolute;
  top: 29px;
  width: 419px;
}

.desktop .image-wrapper {
  background-image: url(./image-3.png);
  background-size: 100% 100%;
  height: 128px;
  position: relative;
}

.desktop .image {
  height: 78px;
  left: 214px;
  object-fit: cover;
  position: absolute;
  top: 26px;
  width: 84px;
}

.desktop .btng {
  height: 74px;
  left: 310px;
  object-fit: cover;
  position: absolute;
  top: 57px;
  width: 79px;
}

.desktop .text-wrapper-2 {
  color: #3b3b3b9d;
  font-family: 'Inter-ExtraLight', Helvetica;
  font-size: 30px;
  font-weight: 200;
  left: 139px;
  letter-spacing: 0;
  line-height: normal;
  position: absolute;
  text-align: center;
  top: 0;
  width: 151px;
}
</style>
