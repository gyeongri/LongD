<template>
  <div class="grid grid-cols-3 grid-rows-3 gap-3">
    <div>
      <p @click="totalView">전체보기</p>
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
const emit = defineEmits(['totalView']);

const totalView = () => {
  emit('totalView');
};
</script>

<style lang="scss" scoped></style>
