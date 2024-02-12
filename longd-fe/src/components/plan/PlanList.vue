<template>
  <div v-for="plan in planList" :key="plan.id">
    {{ plan }} <button @click="goDetail(plan.id)">이동하기</button>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { useUserStore } from '@/stores/user';
import { getPlanList } from '@/utils/api/plan';
import { useRouter } from 'vue-router';

const router = useRouter();

const userStore = useUserStore();

const userInfo = ref();
const planList = ref();

onMounted(() => {
  userInfo.value = userStore.getUserState;

  getPlanList(
    userInfo.value.coupleListId,
    success => {
      console.log('플랜 리스트', success.data);
      planList.value = success.data;
    },
    error => {
      console.log('플랜 리스트 에러', error);
    },
  );
});

const goDetail = id => {
  router.push({ name: 'PlanDetail', params: { id: id } });
};
</script>

<style scoped></style>
