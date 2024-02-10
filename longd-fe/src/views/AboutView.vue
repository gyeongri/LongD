<template>
  <div>
    <div class="navbar bg-base-100">
      <div class="flex-none">
        <button class="btn btn-square btn-ghost" @click="toggleTabs">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            class="inline-block w-5 h-5 stroke-current"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M4 6h16M4 12h16M4 18h16"
            ></path>
          </svg>
        </button>
      </div>
      <div class="flex-1">
        <a class="btn btn-ghost text-xl">daisyUI</a>
      </div>
      <div class="flex-none">
        <button class="btn btn-square btn-ghost" @click="toggleTabs">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            class="inline-block w-5 h-5 stroke-current"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M5 12h.01M12 12h.01M19 12h.01M6 12a1 1 0 11-2 0 1 1 0 012 0zm7 0a1 1 0 11-2 0 1 1 0 012 0zm7 0a1 1 0 11-2 0 1 1 0 012 0z"
            ></path>
          </svg>
        </button>
      </div>
    </div>
    <!-- 탭이 보여질 부분 -->
    <div v-if="showTabs">
      <div role="tablist" class="tabs tabs-bordered">
        <a role="tab" class="tab">Tab 1</a>
        <a role="tab" class="tab tab-active">Tab 2</a>
        <a role="tab" class="tab">Tab 3</a>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

// 탭을 토글하기 위한 상태 변수
const showTabs = ref(false);

// 탭을 토글하는 함수
const toggleTabs = () => {
  showTabs.value = !showTabs.value;
};
</script>

<style lang="scss" scoped></style>
