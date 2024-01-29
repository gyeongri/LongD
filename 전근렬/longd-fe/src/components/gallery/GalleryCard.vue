<template>
  <div
    class="card card-compact w-96 bg-base-100 shadow-xl relative"
    style="height: 300px"
  >
    <figure class="w-full h-full relative">
      <img :src="src" alt="#" class="object-contain w-full h-full" />
      <div
        class="absolute top-0 right-0 m-4 bg-primary text-white px-3 py-1 rounded"
      >
        <input
          type="checkbox"
          class="checkbox checkbox-xs"
          :checked="checked"
          v-if="deleteActive"
          @click.stop="toggleCheckbox"
        />
      </div>
    </figure>
    <div class="card-body">
      <h2 class="card-title">{{ id }}</h2>
    </div>
  </div>
</template>

<script setup>
import { defineEmits, ref } from 'vue';

defineProps({
  id: {
    type: Number,
  },
  src: {
    type: String,
  },
  deleteActive: {
    type: Boolean,
  },
});

const emit = defineEmits(['update:deleteActive']);

const checked = ref(false);

const toggleCheckbox = () => {
  // 부모 컴포넌트에 이벤트를 발송하여 상태를 변경합니다.
  emit('update:deleteActive', !checked.value);
  // checked 값을 토글합니다.
  checked.value = !checked.value;
};
</script>

<style lang="scss" scoped>
/* 추가적인 스타일링을 위한 부분 */
</style>
