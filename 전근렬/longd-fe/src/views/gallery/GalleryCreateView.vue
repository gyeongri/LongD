<template>
  <div>
    <div class="grid grid-cols-3 gap-4">
      <!-- 이미지 미리보기 -->
      <div
        v-for="(image, index) in imagePreviews"
        :key="index"
        class="relative"
      >
        <img :src="image" class="w-full h-auto" alt="Preview" />
        <button
          @click="removeImage(index)"
          class="absolute top-0 right-0 p-1 bg-red-500 text-white rounded-full"
        >
          X
        </button>
      </div>
    </div>
    <!-- 파일 업로드 입력 필드 -->
    <input type="file" multiple @change="handleFileChange" class="mt-4" />
    <!-- 이미지 업로드 버튼 -->
    <button
      @click="uploadImages"
      :disabled="imagePreviews.length === 0"
      class="mt-4 px-4 py-2 bg-blue-500 text-white rounded"
    >
      Upload Images
    </button>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const imagePreviews = ref([]); // 이미지 미리보기를 위한 배열

const handleFileChange = event => {
  const files = event.target.files;
  for (let i = 0; i < files.length; i++) {
    const file = files[i];
    const reader = new FileReader();
    reader.onload = () => {
      imagePreviews.value.push(reader.result); // 이미지 미리보기 배열에 추가
    };
    reader.readAsDataURL(file);
  }
};

const removeImage = index => {
  imagePreviews.value.splice(index, 1); // 이미지 미리보기 삭제
};

const uploadImages = async () => {
  const formData = new FormData();
  for (let i = 0; i < imagePreviews.value.length; i++) {
    formData.append('images[]', imagePreviews.value[i]);
  }

  try {
    // 서버로 이미지 전송하는 API 호출
    // await axios.post('/api/upload', formData);

    // 이미지 업로드 후 이미지 미리보기 배열 초기화
    imagePreviews.value = [];
    alert('Images uploaded successfully!');
  } catch (error) {
    console.error('Error uploading images:', error);
  }
};
</script>

<style>
/* 여기에 필요한 Tailwind CSS 사용자 정의 스타일 추가 가능 */
</style>
