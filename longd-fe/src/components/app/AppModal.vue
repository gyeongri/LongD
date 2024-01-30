<template>
  <dialog :id="modalId" class="modal">
    <div class="modal-box">
      <h3 class="font-bold text-lg">
        <slot name="title"></slot>
      </h3>
      <p class="py-4">
        <slot name="body"></slot>
      </p>
      <div class="modal-action">
        <form method="dialog">
          <slot name="footer">
            <button class="btn">Close</button>
          </slot>
        </form>
      </div>
    </div>
  </dialog>
</template>

<script setup>
defineProps({
  modalId: {
    type: String
  }
})
</script>

<style lang="scss" scoped></style>
