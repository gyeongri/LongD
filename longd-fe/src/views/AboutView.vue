<template>
  <details class="dropdown" ref="dropdown">
    <summary class="m-1 btn">open or close</summary>
    <ul
      class="p-2 shadow menu dropdown-content z-[1] bg-base-100 rounded-box w-52"
      @click="closeDropdown"
    >
      <li><a>Item 1</a></li>
      <li><a>Item 2</a></li>
    </ul>
  </details>
</template>

<script setup>
import { ref } from 'vue';

const dropdown = ref(null);

function closeDropdown() {
  dropdown.value.removeAttribute('open');
}
</script>

<style lang="scss" scoped></style>
