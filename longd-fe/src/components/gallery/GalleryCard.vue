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
          :value="id"
        />
      </div>
    </figure>
    <div class="card-body">
      <h2 class="card-title">{{ id }}</h2>
    </div>
  </div>
</template>

<script setup>
import { ref, watch } from 'vue';

const emit = defineEmits(['checkedEvent']);

const props = defineProps({
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

const checked = ref(false);

const toggleCheckbox = () => {
  checked.value = !checked.value;
  emit('checkedEvent', { id: props.id, checked: checked.value });
};

// deleteActive의 변경을 감시하여 checked를 false로 설정
watch(
  () => props.deleteActive,
  newValue => {
    if (newValue) {
      checked.value = false;
    }
  },
);
</script>

<style lang="scss" scoped>
/* 추가적인 스타일링을 위한 부분 */
</style>
