<template>
  <div class="desktop">
    <div class="div">
      <div class="rectangle"></div>
      <div class="rectangle-2"></div>
    </div>
  </div>
</template>

<script>

</script>

<style>
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

.desktop .rectangle {
  background-color: #d9d9d9;
  height: 617px;
  left: 113px;
  position: absolute;
  top: 177px;
  width: 493px;
}

.desktop .rectangle-2 {
  background-color: #d9d9d9;
  height: 617px;
  left: 807px;
  position: absolute;
  top: 177px;
  width: 493px;
}
</style>