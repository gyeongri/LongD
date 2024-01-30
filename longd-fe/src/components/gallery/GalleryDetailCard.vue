<template>
  <div class="card card-compact w-960 bg-base-100 shadow-xl">
    <figure>
      <img :src="src" alt="#" />
    </figure>
    <div class="card-body">
      <h2 class="card-title">{{ id }}</h2>
    </div>
  </div>
</template>

<script setup>
defineProps({
  id: {
    type: Number,
  },
  src: {
    type: String,
  },
});
</script>

<style lang="scss" scoped></style>
