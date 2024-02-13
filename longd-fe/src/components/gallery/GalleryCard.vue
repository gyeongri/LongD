<template>
  <figure class="relative">
    <img
      :src="src"
      alt="#"
      class="object-cover w-full h-56 overflow-hidden rounded-xl transition-all ease-out duration-500 hover:shadow-2xl hover:transform hover:scale-105"
    />
    <div class="absolute top-0 right-0 m-4 px-3 py-1 rounded">
      <input
        type="checkbox"
        class="checkbox checkbox-error checkbox-s"
        :checked="checked"
        v-if="deleteActive"
        @click.stop="toggleCheckbox"
        :value="id"
      />
    </div>
  </figure>
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
  folderName: {
    type: String,
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
