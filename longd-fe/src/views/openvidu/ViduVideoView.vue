<template>
  <div class="h-[45rem] flex flex-col">
    <div class="border-4 border-blue-500 h-3/4">
      <!-- 사용자의 로컬 주소를 준다면?, 방은 저절로 해결? -->
      <iframe
        :src="VITE_SYNC_BASE_IP"
        width="100%"
        height="100%"
        frameborder="0"
        scrolling="yes"
      ></iframe>
    </div>
    <div class="border-4 border-blue-500 h-1/4">내꺼</div>
  </div>
</template>

<script setup>
const { VITE_SYNC_BASE_IP } = import.meta.env;
</script>

<style lang="scss" scoped></style>
