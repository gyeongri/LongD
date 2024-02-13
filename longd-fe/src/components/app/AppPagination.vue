<template>
  <div class="join">
    <!-- 왼 -->
    <button
      :class="[
        'join-item',
        'btn',
        { 'btn-disabled': isPrevPage },
        'rounded-xl',
        'bg-opacity-0',
        'mr-2',
      ]"
      @click.prevent="$emit('page', currentPage - 1)"
    >
      «
    </button>
    <!-- 가운데 -->
    <!-- 테두리 색깔 주기 -->
    <input
      v-for="page in pageCount"
      :key="page"
      @click.prevent="$emit('page', page)"
      :class="[
        'btn',
        'btn-square',
        { 'btn-active': currentPage === page },
        currentPage === page
          ? 'bg-pink-300 hover:bg-pink-300'
          : 'bg-opacity-0 hover:bg-pink-300',
        'rounded-xl',
        'border',
        'border-pink-300',
        'mr-2',
      ]"
      type="radio"
      name="options"
      :aria-label="page"
    />
    <!-- 오 -->
    <button
      :class="[
        'join-item',
        'btn',
        { 'btn-disabled': isNextPage },
        'bg-opacity-0',
      ]"
      @click.prevent="$emit('page', currentPage + 1)"
    >
      »
    </button>
  </div>
</template>

<script setup>
import { computed } from 'vue';

const props = defineProps({
  currentPage: {
    type: Number,
    required: true,
  },
  pageCount: {
    type: Number,
    required: true,
  },
});

defineEmits(['page']);

const isPrevPage = computed(() => !(props.currentPage > 1));
const isNextPage = computed(() => !(props.currentPage < props.pageCount));
</script>

<style lang="scss" scoped>
/* Scoped styles here */
</style>
