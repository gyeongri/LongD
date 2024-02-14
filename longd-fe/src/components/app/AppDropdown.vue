<template>
  <div class="dropdown dropdown-end">
    <div
      tabindex="0"
      role="button"
      class="btn btn-circle btn-ghost text-black-400"
    >
      <svg
        class="swap-off fill-current"
        xmlns="http://www.w3.org/2000/svg"
        width="32"
        height="32"
        viewBox="0 0 512 512"
      >
        <path
          d="M64,384H448V341.33H64Zm0-106.67H448V234.67H64ZM64,128v42.67H448V128Z"
        />
      </svg>
    </div>
    <ul
      tabindex="0"
      class="dropdown-content z-[1] menu p-2 shadow bg-base-100 rounded-box w-[7rem]"
    >
      <slot @click="handleSlotClick"></slot>
    </ul>
  </div>
</template>

<script setup></script>

<style scoped></style>
