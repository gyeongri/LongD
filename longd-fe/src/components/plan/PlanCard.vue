<template>
  <div
    class="card card-compact w-80 bg-base-100 shadow-2xl transition-all ease-out duration-500 hover:shadow-2xl hover:transform hover:scale-105"
  >
    <figure>
      <img
        src="https://i.pinimg.com/564x/b4/bd/75/b4bd756f0d38cf1589b6a9ebc8b5fc32.jpg"
        alt="#"
        class="object-cover w-full h-64 overflow-hidden rounded-xl"
      />
    </figure>
    <div class="card-body flex items-end mr-8">
      <h2 class="card-title">{{ title }}</h2>
      <p></p>
      <div class="card-actions justify-end">
        <p>{{ dateStart }} - {{ dateEnd }}</p>
      </div>
    </div>
  </div>
</template>

<script setup>
defineProps({
  id: {
    type: Number,
  },
  title: {
    type: String,
  },
  src: {
    type: String,
  },
  dateStart: {
    type: String,
  },
  dateEnd: {
    type: String,
  },
});
</script>

<style lang="scss" scoped></style>
