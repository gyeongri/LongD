<template>
  <div>
    {{ videoList }}
  </div>
</template>

<script setup>
import { getVideo } from '@/utils/api/albums';
import { onMounted, ref } from 'vue';
const videoList = ref([]);
onMounted(() => {
  getVideo()
    .then(res => {
      videoList.value = res.data;
      console.log(res.data);
    })
    .catch(error => {
      console.error(error);
    });
});
</script>

<style scoped></style>
