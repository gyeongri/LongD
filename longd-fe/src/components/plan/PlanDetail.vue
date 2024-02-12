<template>
  <div v-for="plan in planInfoDetail" :key="plan.id">
    {{ plan }}
  </div>
</template>

<script setup>
import { ref, onMounted, watchEffect } from 'vue';
import { useRoute } from 'vue-router';
import { getPlanDetail } from '@/utils/api/plan';

const currentId = ref('');
const planInfoDetail = ref();

const router = useRoute();

const getCurrentRouteId = () => {
  currentId.value = router.params.id;
};

// 컴포넌트가 마운트될 때와 라우터의 변경을 감지하여 현재 ID를 업데이트합니다.
onMounted(() => {
  getCurrentRouteId;

  getPlanDetail(
    currentId.value,
    success => {
      console.log(success.data);
      planInfoDetail.value = success.data;
    },
    error => {
      console.log(error);
    },
  );
});
watchEffect(getCurrentRouteId);
</script>

<style scoped></style>
