<template>
  <div class="flex justify-between mb-10 mr-10">
    <button class="ml-10" @click="goFolder">
      <font-awesome-icon icon="fa-solid fa-arrow-left" size="lg" />
    </button>
    <div class="flex">
      <GalleryFilter
        v-model:id="params.id_like"
        :limit="params._limit"
        @update:limit="changeLimit"
        class="mx-2"
      >
      </GalleryFilter>

      <div class="flex items-center space-x-4">
        <button
          v-if="deleteActive"
          @click="removeItems"
          class="btn btn-outline btn-primary"
        >
          삭제
        </button>
        <AppDropdown>
          <template v-slot>
            <li onclick="gallery_create.showModal()">
              <a class="font-bold">추가</a>
            </li>
            <li v-if="!deleteActive" @click="toggleDelete">
              <a class="font-bold">삭제</a>
            </li>
            <li v-if="deleteActive" @click="toggleDelete">
              <a class="font-bold">삭제 취소 </a>
            </li>
          </template>
        </AppDropdown>
      </div>
    </div>
  </div>

  <AppGrid class="mb-10" :items="items">
    <template v-slot="{ item }">
      <GalleryCard
        :src="item.pathUrl"
        :id="item.id"
        :deleteActive="deleteActive"
        @click="goDetail(folderName, item.id)"
        @checkedEvent="totalCheckedEvent"
      ></GalleryCard>
    </template>
  </AppGrid>

  <AppModal modalId="gallery_create">
    <template v-slot:title>사진을 등록하세요</template>
    <template v-slot:body>
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
    </template>

    <template v-slot:footer>
      <!-- 파일 업로드 입력 필드 -->
      <label
        for="file-upload"
        class="btn btn-outline border-pink-400 mt-4 text-gray-500 hover:bg-pink-300"
      >
        파일 선택하기
      </label>
      <input
        id="file-upload"
        type="file"
        multiple
        @change="handleFileChange"
        class="hidden"
      />
      <button
        @click="uploadImages"
        :disabled="imagePreviews.length === 0"
        class="btn btn-outline border-pink-400 mt-4 text-gray-500 hover:bg-pink-300 mx-2"
      >
        등록하기
      </button>
      <button
        @click="cancelImages"
        class="btn btn-outline border-gray-400 hover:bg-gray-300 mx-2"
      >
        취소
      </button>
    </template>
  </AppModal>
  <div class="flex justify-center">
    <AppPagination
      v-if="route.params.folderName === '전체보기'"
      :current-page="params._page"
      :page-count="pageCount"
      @page="page => (params._page = page)"
    />
    <AppPagination
      v-else
      :current-page="params2._page"
      :page-count="pageCount"
      @page="page => (params2._page = page)"
    />
  </div>
</template>

<script setup>
import AppModal from '@/components/app/AppModal.vue';
import AppDropdown from '@/components/app/AppDropdown.vue';
import GalleryFilter from '@/components/gallery/GalleryFilter.vue';
import GalleryCard from '@/components/gallery/GalleryCard.vue';
import AppPagination from '@/components/app/AppPagination.vue';
import AppGrid from '@/components/app/AppGrid.vue';
import { ref, computed, watchEffect, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import {
  getGalleryTotalList,
  createGallery,
  deleteGallery,
  getGalleryFolderList,
} from '@/utils/api/albums';
import { uploadImage } from '@/utils/api/photo';
import { useGalleryStore } from '@/stores/gallery.js';
const galleryStore = useGalleryStore();

const route = useRoute();
const router = useRouter();

// 조회 관련 파라미터(전체)
const params = ref({
  _limit: 6, // 몇개씩 조회
  _page: 1, // 현재 페이지를 조회
  _sort: 'id', // 무엇을
  _order: 'desc', // 내림차순
  // id_like: '', // 해당 요소 검색 기능
});

// 조회 관련 파라미터(폴더별)
const params2 = ref({
  _limit: 6, // 몇개씩 조회
  _page: 1, // 현재 페이지를 조회
  _sort: 'id', // 무엇을
  _order: 'desc', // 내림차순
  categoryName: route.params.folderName, // 조회할 폴더 이름
});

// 페이지네이션에서 페이지의 수는 전체 item 수로 결정남
const totalCount = ref(0);
const pageCount = computed(() =>
  Math.ceil(totalCount.value / params.value._limit),
);

// 한 화면에 보이는 item 갯수 제한 조절
const changeLimit = value => {
  params.value._limit = value;
  params.value._page = 1; // 갯수 제한이 바뀔 때마다 첫 페이지로 가도록
};

// 삭제 체크된 id 배열
const totalChecked = ref([]);

// emit으로 보낸 데이터 받을 때의 표본
// card에서 보낸 데이터를 보고 check 여부 판단하여 배열에 추가해줌
const totalCheckedEvent = data => {
  if (data.checked) {
    // data.checked가 true이면 totalChecked 배열에 data.id 추가
    totalChecked.value.push(data.id);
  } else {
    // data.checked가 false이면 totalChecked 배열에서 data.id 제거
    const index = totalChecked.value.indexOf(data.id);
    if (index !== -1) {
      totalChecked.value.splice(index, 1);
    }
  }
  console.log(totalChecked.value);
};

const coupleId = ref(1);
// 해당 폴더의 리스트 조회
const items = ref([]);
const fetchAlbums = async () => {
  if (route.params.folderName === '전체보기') {
    console.log('전체보기');
    try {
      const { data } = await getGalleryTotalList(coupleId.value, params.value);
      console.log(data);
      items.value = data;
      totalCount.value = data[0].size;
    } catch (err) {
      console.error(err);
    }
  } else {
    // 서버 켜지면 test 필요.
    // 폴더명으로 필터처리 후 조회(백에서 해줌)
    console.log('폴더 조회');
    try {
      const { data } = await getGalleryFolderList(
        coupleId.value,
        params2.value,
      );
      items.value = data;
      totalCount.value = data[0].size;
      // totalCount.value = data.length;
      // console.log(data);
    } catch (err) {
      console.error(err);
    }
  }
};

// watchEffect는 watch와 동일하지만 처음에 한번 바로 실행해주는 점이 다름
watchEffect(fetchAlbums);

// 현재 폴더명
const folderName = route.params.folderName;

// 상세 페이지로 이동
const goDetail = (folderName, id) => {
  // deleteActive가 false인 경우에만 goDetail 함수 실행
  if (!deleteActive.value) {
    router.push({
      name: 'GalleryDetail',
      params: {
        folderName,
        id,
      },
    });
  }
};

// 삭제 버튼 활성화 여부
const deleteActive = ref(false);

// 삭제 버튼 토글
const toggleDelete = () => {
  deleteActive.value = !deleteActive.value;
  totalChecked.value = []; // totalChecked 리스트를 빈 배열로 설정
};

// 삭제 체크 표시된 item들 삭제

const removeItems = async () => {
  if (confirm('정말로 삭제하시겠습니까?') === false) {
    return;
  }
  await deleteGallery(totalChecked.value);
  await fetchAlbums();
  // 삭제 메소드만 넣으면 끝
  // 근데 왜 체크 표시 안 없어질 것 같지???

  deleteActive.value = !deleteActive.value;
  totalChecked.value = []; // totalChecked 리스트를 빈 배열로 설정
  console.log('삭제');
};

// 생성 모달 관련
const images = ref([]); // 저장될 이미지
const imagePreviews = ref([]); // 이미지 미리보기를 위한 배열
const handleFileChange = event => {
  const files = event.target.files;
  for (let i = 0; i < files.length; i++) {
    const file = files[i];
    images.value.push(file);
    const reader = new FileReader();
    reader.onload = () => {
      imagePreviews.value.push(reader.result); // 이미지 미리보기 배열에 추가
    };
    reader.readAsDataURL(file);
  }
};

const removeImage = index => {
  imagePreviews.value.splice(index, 1); // 이미지 미리보기 삭제
  images.value.splice(index, 1); // 저장될 이미지 삭제
};

let categoryId = null;
const getCategoryId = async () => {
  // 폴더명 리스트 조회
  await galleryStore.addFolderNameList(coupleId.value);
  // 현재 카테고리명 찾기
  if (route.params.folderName !== '전체보기') {
    const folder = galleryStore.folderNameList.find(
      folder => folder.name === route.params.folderName,
    );
    if (folder) {
      categoryId = folder.id;
      console.log(categoryId);
    }
  }
};

const pathUrlList = ref([]);
const formData2 = [];
const uploadImages = async () => {
  const formData = new FormData();
  for (let i = 0; i < images.value.length; i++) {
    formData.append('file', images.value[i]);
    await uploadImage(
      formData,
      success => {
        pathUrlList.value = success.data;
        console.log(pathUrlList.value);

        let data = {};
        for (let i = 0; i < pathUrlList.value.length; i++) {
          data = {
            pathUrl: pathUrlList.value[i],
            categoryId: categoryId,
          };
          console.log(data);
          formData2.push(data);
          console.log(formData2);
          // 여기로
        }
      },
      success2 => {
        console.log(formData2);
        console.log(coupleId.value);
        createGallery(formData2);
        // 이미지 업로드 후 이미지 미리보기 배열 초기화
        fetchAlbums();
      },
      error => {
        console.log('사진을 변환할 수 없어요.', error);
      },
    );
  }
};

// 취소했을 때도 미리보기 남아있는 것을 방지하기 위함
const cancelImages = () => {
  imagePreviews.value = [];
};

// folder화면으로 가기
const goFolder = () => {
  router.push({
    name: 'GalleryFolder',
  });
};

onMounted(() => {
  getCategoryId();
});

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
