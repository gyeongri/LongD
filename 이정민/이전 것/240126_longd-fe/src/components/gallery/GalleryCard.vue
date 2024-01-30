<template>
  <div class="card card-compact w-96 bg-base-100 shadow-xl">
    <figure>
      <img :src="src" alt="#" />
    </figure>
    <div class="card-body">
      <h2 class="card-title">Shoes!</h2>
      <p>If a dog chews shoes whose shoes does he choose?</p>
      <div class="card-actions justify-end">
        <button class="btn btn-primary">Buy Now</button>
      </div>
    </div>
  </div>
</template>

<script setup>
defineProps({
  src: {
    type: String,
  },
});
</script>

<style lang="scss" scoped></style>
