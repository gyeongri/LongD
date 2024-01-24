<template>
  <div v-if="streamManager">
    <OvVideo :stream-manager="streamManager" />
    <div>
      <p>{{ clientData.value }}</p>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import OvVideo from './OvVideo.vue';

const getConnectionData = () => {
  const { connection } = streamManager.stream;
  return JSON.parse(connection.data);
};

const clientData = computed(() => {
  const { clientData } = getConnectionData();
  return clientData;
});
</script>
