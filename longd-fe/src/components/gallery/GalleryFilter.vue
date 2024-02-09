<template>
  <form @submit.prevent>
    <div class="flex items-center justify-between">
<<<<<<< HEAD:전근렬/longd-fe/src/components/gallery/GalleryFilter.vue
      <div class="w-3/4">
=======
      <!-- <div class="w-3/4">
>>>>>>> f1d41673cfc69d2c419b4645f6dfdd209d7f64bf:longd-fe/src/components/gallery/GalleryFilter.vue
        <input
          :value="id"
          @input="changeId"
          type="text"
          class="form-control"
          placeholder="id로 검색해주세요."
        />
<<<<<<< HEAD:전근렬/longd-fe/src/components/gallery/GalleryFilter.vue
      </div>
      <div class="w-1/4 pl-2">
=======
      </div> -->
      <div class="pl-2">
>>>>>>> f1d41673cfc69d2c419b4645f6dfdd209d7f64bf:longd-fe/src/components/gallery/GalleryFilter.vue
        <select
          :value="limit"
          @input="$emit('update:limit', $event.target.value)"
          class="select select-bordered w-full max-w-xs"
        >
          <option value="6">6개씩 보기</option>
          <option value="12">12개씩 보기</option>
          <option value="18">18개씩 보기</option>
        </select>
      </div>
    </div>
  </form>
</template>

<script setup>
defineProps({
  id: { type: [String, Number] },
  limit: { type: [String, Number] },
});
defineEmits(['update:id', 'update:limit']);
// const changeId = event => {
//   setTimeout(() => {
//     emit('update:id', event.target.value);
//   }, 500);
// };
</script>

<style lang="scss" scoped></style>
