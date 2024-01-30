<template>
  <form @submit.prevent>
    <div class="flex items-center justify-between">
      <div class="w-3/4">
        <input
          :value="id"
          @input="changeId"
          type="text"
          class="form-control"
          placeholder="id로 검색해주세요."
        />
      </div>
      <div class="w-1/4 pl-2">
        <select
          :value="limit"
          @input="$emit('update:limit', $event.target.value)"
          class="form-select"
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
  limit: { type: [String, Number] }
})
const emit = defineEmits(['update:id', 'update:limit'])
const changeId = (event) => {
  setTimeout(() => {
    emit('update:id', event.target.value)
  }, 500)
}
</script>

<style lang="scss" scoped></style>
