<template>
  <div class="test">
    <MapMenu></MapMenu>
    <Map></Map>
  </div>
</template>

<script setup>
import MapMenu from '@/components/plan/MapMenu.vue';
import Map from '@/components/plan/Map.vue';
</script>

<style scoped>
.test {
  display: flex;
}
</style>
