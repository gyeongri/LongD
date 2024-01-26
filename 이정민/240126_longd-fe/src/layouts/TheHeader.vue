<template>
  <div class="navbar bg-base-100">
    <div>
      <RouterLink class="btn btn-ghost text-xl" :to="{ name: 'Home' }"
        >롱디</RouterLink
      >
    </div>

    <div>
      <RouterLink :to="{ name: 'GalleryList' }"
        ><img class="image" alt="Gallery" src="/public/static/img/image.png"
      /></RouterLink>
      <RouterLink :to="{ name: 'Carender' }">
        <img class="img" alt="carender" src="/public/static/img/2x.png" />
      </RouterLink>
      <RouterLink :to="{ name: 'Facecall' }">
        <img class="image-2" alt="facecall" src="/public/static/img/im.png" />
      </RouterLink>
      <RouterLink :to="{ name: 'Map' }">
        <img class="image-3" alt="map" src="/public/static/img/1.png" />
      </RouterLink>
      <RouterLink :to="{ name: 'Joy' }">
        <img class="image-4" alt="joy" src="/public/static/img/1-2x.png" />
      </RouterLink>
    </div>

    <div class="flex-none">
      <ul class="menu menu-horizontal px-1">
        <li><RouterLink :to="{ name: 'GalleryList' }">Gallery</RouterLink></li>
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
