<template>
  <div class="flex justify-end">
    <button
      class="btn btn-outline btn-sm border-red-300 hover:bg-red-400 hover:border-red-300 mr-4 mb-2"
      @click="showManual"
    >
      사용 설명서
    </button>
  </div>
  <div class="h-[40rem] ml-6">
    <!-- 사용자의 로컬 주소를 준다면?, 방은 저절로 해결? -->
    <iframe
      :src="VITE_SYNC_BASE_IP"
      width="100%"
      height="100%"
      frameborder="0"
      scrolling="yes"
    ></iframe>
  </div>
</template>

<script setup>
import Swal from 'sweetalert2';
const { VITE_SYNC_BASE_IP } = import.meta.env;
const showManual = () => {
  Swal.fire({
    title:
      '1. 아래의 "더하기" 버튼을 사용하여 비디오 URL을 추가해 원하는 영상을 틀 수 있습니다.' +
      '<br>' +
      '2. "리더" 버튼을 통해 상대방과 자신의 동영상을 동시에 통제할 수 있습니다.',
    allowEscapeKey: false,
    confirmButtonColor: '#FF9CBD',
  });
};
</script>

<style lang="scss" scoped></style>
