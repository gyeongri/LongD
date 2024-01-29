<template>
  <div>
    <!-- v-model 시리즈 -->
    <!-- 단순 input 입력 -->
    <input v-model="inputText">
    <p>{{ inputText }}</p>
    
    <!-- computed -->
    {{ computedTest }}
    
    <!-- checkbox -->
    <input type="checkbox" id="check" value="choice" v-model="checkTrue">
    <input type="checkbox" id="check1" value="test" v-model="checkTrue">
    <input type="checkbox" id="check2" v-model="checked">
    <label class="" for="check">{{ checkTrue }}</label>
    <!-- select -->
    <p>{{ selected }}</p>
    <select v-model="selected">
      <option value="">선택해주세요</option>
      <!-- value=""면 기본값으로 설정되게 된다 -->
      <!-- disabled를 넣으면 선택불가로 된다 -->
      <option value="1번">1번</option>
      <option value="2번">2번</option>
      <option value="3번">3번</option>
    </select>

  </div>
</template>

<script setup>
import { ref, watch, computed } from 'vue'
// 문자열로 vue 써주는 거 잊지말기!
const inputText = ref('')
const checked = ref('')
// 이렇게하면 true/false로 나눠진다
const checkTrue = ref([])
// 이렇게하면 원하는 값이 담긴다

const selected = ref('')

// computed
const computedTest = computed(() => {
  return checkTrue.value.length > 0? '체크박스가 선택되었습니다.' : '체크박스를 선택해주세요.'
})

// 관찰자 써서 그대로 넣을 수 있게 하는 것!
watch(checked, (newValue) => {
  if (newValue === true) {
    checkTrue.value.push(checked.value)
  }
  // 누를때마다 넣는 것!
  else {  
    const index = checkTrue.value.indexOf(true)
    // true가 있는 인덱스를 찾아서
    checkTrue.value.splice(index,1)
    // 해당 인덱스의 값을 지우기!
  }
})

watch(checkTrue, (newValue, oldValue) => {
  // (감시할 친구, 콜백함수(새로운 값, 이전 값))
  if (newValue) {
    checkTrue.value.sort()
  }
})

</script>

<style scoped>

</style>