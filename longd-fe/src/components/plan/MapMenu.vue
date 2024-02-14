<template>
  <div class="mapmenu">
    <!-- <div class="drawer drawer-open">
      <input id="my-drawer-2" type="checkbox" class="drawer-toggle" />
      <div class="drawer-side">
        <div class="menu p-4 w-80 min-h-full bg-base-200 text-base-content"> -->
    <div class="flex gap-2 border-b border-red-200">
      <button class="text-red-400">
        <RouterLink :to="{ name: 'MapSearch' }">장소검색</RouterLink>
      </button>
      <button class="text-red-400">
        <RouterLink :to="{ name: 'MapPlan' }">일정짜기</RouterLink>
      </button>
    </div>
    <div class="routerview">
      <RouterView></RouterView>
    </div>
  </div>
</template>

<script setup></script>
