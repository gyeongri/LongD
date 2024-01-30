<script setup>

import { useRouter } from 'vue-router';

const { VITE_BASE_IP } = import.meta.env;

const router = useRouter();


const eci = () => {
      console.log("떳냐")
      router.push({ name: "loginPage"});
    }
  

</script>

<template>


<div>

  <a :href="VITE_BASE_IP + '/user/customlogin'">
    <button @click="test">행복버튼</button>
  </a> <br>
    <button @click="eci">익환버튼</button> <br>
</div>

</template>



<style>

</style>