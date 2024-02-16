<template>
  <div v-for="bucket in bucketList" :key="bucket.id">
    {{ bucket }}
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { useUserStore } from '@/stores/user';
import { getBucketList } from '@/utils/api/bucketList';
// import { useRouter } from 'vue-router';

// const router = useRouter();

const userStore = useUserStore();

const userInfo = ref();
const bucketList = ref();

onMounted(() => {
  userInfo.value = userStore.getUserState;

  getBucketList(
    userInfo.value.coupleListId,
    success => {
      bucketList.value = success.data;
    },
    error => {
      console.log('버킷 리스트 에러', error);
    },
  );
});
</script>

<style scoped></style>
