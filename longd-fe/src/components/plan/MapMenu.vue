<template>
  <div class="mapmenu">
    <!-- <div class="drawer drawer-open">
      <input id="my-drawer-2" type="checkbox" class="drawer-toggle" />
      <div class="drawer-side">
        <div class="menu p-4 w-80 min-h-full bg-base-200 text-base-content"> -->
    <div class="flex justify-center gap-1 border-b border-red-200 mb-1">
      <RouterLink :to="{ name: 'MapSearch' }">
        <button class="btn btn-sm mr-1" style="background-color: #fac1c1">
          장소검색
        </button></RouterLink
      >

      <RouterLink :to="{ name: 'MapPlan' }">
        <button class="btn btn-sm mr-1" style="background-color: #fac1c1">
          일정짜기
        </button></RouterLink
      >
    </div>
    <div class="routerview">
      <RouterView></RouterView>
    </div>
  </div>
</template>

<script setup></script>
