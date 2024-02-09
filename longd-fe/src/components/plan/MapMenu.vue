<template>
  <div>
    <div class="drawer drawer-open">
      <input id="my-drawer-2" type="checkbox" class="drawer-toggle" />
      <div class="drawer-side">
        <div class="menu p-4 w-80 min-h-full bg-base-200 text-base-content">
          <div class="test">
            <button class="btn btn-outline btn-error">
              <RouterLink :to="{ name: 'MapSearch' }">장소검색</RouterLink>
            </button>
            <button class="btn btn-outline btn-error">
              <RouterLink :to="{ name: 'MapPlan' }">일정짜기</RouterLink>
            </button>
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
