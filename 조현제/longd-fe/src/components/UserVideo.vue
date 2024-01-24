<template>
  <div v-if="streamManager">
    <OvVideo :stream-manager="streamManager" />
    <div>
      <p>{{ clientData.value }}</p>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, defineProps } from 'vue'; // Import defineProps
import OvVideo from './OvVideo.vue';

// Use defineProps to receive props
const props = defineProps(['streamManager']);

const getConnectionData = () => {
  if (!props.streamManager || !props.streamManager.stream) {
    return {};
  }
  const { connection } = props.streamManager.stream;
  return JSON.parse(connection.data);
};

const clientData = computed(() => {
  const { clientData } = getConnectionData();
  return clientData;
});
</script>
