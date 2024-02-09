<template>
  <div>
    <div class="drawer lg:drawer-open">
      <input id="my-drawer-2" type="checkbox" class="drawer-toggle" />
      <div class="drawer-content flex flex-col items-center justify-center">
        <!-- Page content here -->
        <label for="my-drawer-2" class="btn btn-primary drawer-button lg:hidden"
          >Search & Plan</label
        >
      </div>
      <div class="drawer-side">
        <label
          for="my-drawer-2"
          aria-label="close sidebar"
          class="drawer-overlay"
        ></label>
        <div class="menu p-4 w-80 min-h-full bg-base-200 text-base-content">
          <!-- Sidebar content here -->
          <div class="test">
          <button class="btn btn-outline btn-error"><RouterLink :to="{ name: 'MapSearch' }">장소검색</RouterLink></button>
          <button class="btn btn-outline btn-error"><RouterLink :to="{ name: 'MapPlan' }">일정짜기</RouterLink></button>
          </div>
          <RouterView></RouterView>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
.menu .test {
  display: flex;
}
</style>
