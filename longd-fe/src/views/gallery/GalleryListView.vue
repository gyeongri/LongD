<template>
  <div class="flex justify-between">
    <GalleryFilter
      v-model:id="params.id_like"
      :limit="params._limit"
      @update:limit="changeLimit"
    >
    </GalleryFilter>

    <div class="flex items-center space-x-4">
      <button v-if="deleteActive" class="btn btn-outline btn-primary">
        삭제
      </button>
      <AppDropdown>
        <template v-slot>
          <li onclick="gallery_create.showModal()">
            <a>open modal</a>
          </li>
          <li @click="goCreate"><a>사진 추가</a></li>
          <li @click="toggleDelete"><a>사진 삭제</a></li>
        </template>
      </AppDropdown>
    </div>
  </div>

  <AppGrid :items="items">
    <template v-slot="{ item }">
      <GalleryCard
        :src="item.src"
        :id="item.id"
        :deleteActive="deleteActive"
        @click="goDetail(item.id)"
      ></GalleryCard>
    </template>
  </AppGrid>

  <AppModal modalId="gallery_create">
    <template v-slot:title>사진을 등록하세요</template>
    <template v-slot:body>
      <div class="mb-4">
        <GalleryCard v-if="src" :src="src" />
        <GalleryCard v-else :src="defaultImage" />
        <input
          type="file"
          class="file-input file-input-bordered file-input-primary w-full max-w-xs"
          @change="previewImage"
        />
      </div>
    </template>
    <template v-slot:footer>
      <button class="btn btn-outline btn-primary mx-2">취소</button>
      <button @click="save" class="btn btn-outline btn-primary">저장</button>
    </template>
  </AppModal>

  <AppPagination
    :current-page="params._page"
    :page-count="pageCount"
    @page="page => (params._page = page)"
  />
</template>

<script setup>
import AppModal from '@/components/app/AppModal.vue';
import AppDropdown from '@/components/app/AppDropdown.vue';
import GalleryFilter from '@/components/gallery/GalleryFilter.vue';
import GalleryCard from '@/components/gallery/GalleryCard.vue';
import AppPagination from '@/components/app/AppPagination.vue';
import AppGrid from '@/components/app/AppGrid.vue';
import { ref, computed, watchEffect } from 'vue';
import { useRouter } from 'vue-router';
import { getAlbums } from '@/utils/api/albums';
import { createAlbum } from '@/utils/api/albums';

const router = useRouter();

const params = ref({
  _sort: 'createdAt', // 무엇을
  _order: 'desc', // 내림차순
  _limit: 6, // 몇개씩 조회
  _page: 1, // 현재 페이지를 조회
  id_like: '', // 해당 요소 검색 기능
});

const totalCount = ref(0);
const pageCount = computed(() =>
  Math.ceil(totalCount.value / params.value._limit),
);

const changeLimit = value => {
  params.value._limit = value;
  params.value._page = 1; // 갯수 제한이 바뀔 때마다 첫 페이지로 가도록
};

const items = ref([]);

const fetchAlbums = async () => {
  try {
    const { data, headers } = await getAlbums(params.value);
    items.value = data;
    totalCount.value = headers['x-total-count'];
    // console.log(data);
  } catch (err) {
    console.err(err);
  }
};

// watchEffect는 watch와 동일하지만 처음에 한번 바로 실행해주는 점이 다름
watchEffect(fetchAlbums);

const goDetail = id => {
  router.push({
    name: 'GalleryDetail',
    params: {
      id,
    },
  });
};

const goCreate = () => {
  router.push({
    name: 'GalleryCreate',
  });
};

const deleteActive = ref(false);

const toggleDelete = () => {
  deleteActive.value = !deleteActive.value;
};

// 모달 관련
const src = ref('');

const defaultImage = '/src/assets/images/icon_upload.png'; // 디폴트 이미지 경로

const previewImage = event => {
  const file = event.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = () => {
      src.value = reader.result;
    };
    reader.readAsDataURL(file);
  } else {
    src.value = '';
  }
};

const save = async () => {
  try {
    console.log(src);
    await createAlbum({
      src: 'https://daisyui.com/images/stock/photo-1606107557195-0e29a4b5b4aa.jpg',
      createdAt: Date.now(),
    });
    router.push({ name: 'GalleryList' });
  } catch (err) {
    console.log(err);
  }
};

// // 참고 (데이터 전송 관련 방법 2가지)
// // 데이터 받는 방식 1
// const fetchAlbums2 = () => {
//   getAlbums()
//     .then(res => {
//       console.log('res', res);
//     })
//     .catch(err => {
//       console.log('err', err);
//     });
// };
// fetchAlbums2();
// // 데이터 받는 방식 2
// const fetchAlbums3 = async () => {
//   try {
//     const res = await getAlbums();
//     console.dir(res);
//   } catch (err) {
//     console.err(err);
//   }
// };
// fetchAlbums3();
</script>

<style lang="scss" scoped></style>
