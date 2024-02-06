<template>
  <div class="grid grid-cols-3 grid-rows-3 gap-3">
    <div>
      <p>전체보기</p>
    </div>
    <div>
      <p>추억영상함</p>
    </div>
    <div v-for="(item, index) in items" :key="index" :class="colClass">
      <slot :item="item" :index="index"></slot>
    </div>
  </div>
</template>

<script setup>
defineProps({
  items: {
    type: Array,
    required: true,
  },
  colClass: {
    type: String,
    default: 'col-span-1',
  },
});
</script>

<style lang="scss" scoped></style>
