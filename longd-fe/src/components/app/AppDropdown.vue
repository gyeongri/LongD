<template>
<<<<<<< HEAD:전근렬/longd-fe/src/components/app/AppDropdown.vue
  <div class="dropdown dropdown-end">
    <!-- 햄버거 버튼 -->
    <label for="menu-toggle" class="btn btn-circle swap swap-rotate">
      <!-- this hidden checkbox controls the state -->
      <input id="menu-toggle" type="checkbox" />

      <!-- hamburger icon -->
=======
  <details class="dropdown dropdown-end">
    <summary class="btn btn-circle">
>>>>>>> f1d41673cfc69d2c419b4645f6dfdd209d7f64bf:longd-fe/src/components/app/AppDropdown.vue
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
<<<<<<< HEAD:전근렬/longd-fe/src/components/app/AppDropdown.vue

      <!-- close icon -->
      <svg
        class="swap-on fill-current"
        xmlns="http://www.w3.org/2000/svg"
        width="32"
        height="32"
        viewBox="0 0 512 512"
      >
        <polygon
          points="400 145.49 366.51 112 256 222.51 145.49 112 112 145.49 222.51 256 112 366.51 145.49 400 256 289.49 366.51 400 400 366.51 289.49 256 400 145.49"
        />
      </svg>
    </label>

    <!-- 드롭다운 메뉴 -->
    <ul
      tabindex="0"
      class="dropdown-content z-[1] menu p-2 shadow bg-base-100 rounded-box w-52"
    >
      <slot></slot>
    </ul>
  </div>
=======
    </summary>

    <ul
      class="p-2 shadow menu dropdown-content z-[1] bg-base-100 rounded-box w-52"
    >
      <slot></slot>
    </ul>
  </details>
>>>>>>> f1d41673cfc69d2c419b4645f6dfdd209d7f64bf:longd-fe/src/components/app/AppDropdown.vue
</template>

<script setup></script>

<style lang="scss" scoped></style>
