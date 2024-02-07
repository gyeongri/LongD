<template>
  <!-- h-1/4, h-3/4 - 차지할 비율을 나타냄, w-1/4도 있음(가로버전) -->
  <div class="h-[45rem] flex flex-col">
    <div class="border-4 border-blue-500 h-3/4">chatting1</div>
    <div class="border-4 border-blue-500 h-1/4">
      <div class="border-4 border-blue-300 h-3/4">chatting2</div>
      <div class="border-4 border-blue-300 h-1/4 flex justify-end">
        <button class="btn btn-outline btn-primary">전송</button>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style lang="scss" scoped></style>
