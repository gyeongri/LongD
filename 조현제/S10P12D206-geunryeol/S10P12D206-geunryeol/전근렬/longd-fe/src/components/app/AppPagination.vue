<template>
  <div class="join">
    <!-- 왼 -->
    <button
      :class="['join-item', 'btn', { 'btn-disabled': isPrevPage }]"
      class="join-item btn"
      @click.prevent="$emit('page', currentPage - 1)"
    >
      «
    </button>
    <!-- 가운데 -->
    <input
      v-for="page in pageCount"
      :key="page"
      @click.prevent="$emit('page', page)"
      class="join-item btn btn-square"
      :class="{ 'btn-active': currentPage === page }"
      type="radio"
      name="options"
      :aria-label="page"
    />
    <!-- 오 -->
    <button
      :class="['join-item', 'btn', { 'btn-disabled': isNextPage }]"
      @click.prevent="$emit('page', currentPage + 1)"
    >
      »
    </button>
  </div>
</template>

<script setup>
import { computed } from 'vue'

const props = defineProps({
  currentPage: {
    type: Number,
    required: true
  },
  pageCount: {
    type: Number,
    required: true
  }
})

defineEmits(['page'])

const isPrevPage = computed(() => !(props.currentPage > 1))
const isNextPage = computed(() => !(props.currentPage < props.pageCount))
</script>

<style lang="scss" scoped></style>
