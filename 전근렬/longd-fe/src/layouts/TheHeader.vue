<template>
  <div class="navbar bg-base-100">
    <div class="flex-1">
      <RouterLink class="btn btn-ghost text-xl" :to="{ name: 'Home' }"
        >롱디</RouterLink
      >
    </div>
    <div class="flex-none">
      <ul class="menu menu-horizontal px-1">
        <li><RouterLink :to="{ name: 'GalleryList' }">Gallery</RouterLink></li>
        <li><RouterLink :to="{ name: 'Calendar' }">Calendar</RouterLink></li>
        <li>
          <details>
            <summary>profile</summary>
            <ul class="p-2 bg-base-100 rounded-t-none">
              <li><RouterLink :to="{ name: 'Home' }">Home</RouterLink></li>
              <li><RouterLink :to="{ name: 'About' }">About</RouterLink></li>
            </ul>
          </details>
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup></script>

<style lang="scss" scoped></style>
