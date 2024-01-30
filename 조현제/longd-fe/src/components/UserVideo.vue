<template>
  <div v-if="streamManager">
    <OvVideo :stream-manager="props.streamManager" />
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
console.log(props.streamManager);
console.log(props);

const getConnectionData = () => {
  if (!props.streamManager || !props.streamManager.stream) {
    return {};
  }
  const { connection } = props.streamManager.stream;
  console.log(connection);
  return JSON.parse(connection.data);
};

const clientData = computed(() => {
  const { clientData } = getConnectionData();
  return clientData;
});
</script>
