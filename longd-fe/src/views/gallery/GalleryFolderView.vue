<template>
  <div class="flex justify-end mb-10 mr-10">
    <div class="flex items-center space-x-4" style="z-index: 999">
      <AppDropdown>
        <template v-slot>
          <li @click="folderCreate"><a>폴더 생성</a></li>
          <li @click="folderDelete"><a>폴더 삭제</a></li>
        </template>
      </AppDropdown>
    </div>
  </div>

  <GalleryFolderGrid :items="folderFirstItem" @totalView="totalView">
    <template v-slot="{ item }">
      <div class="stack w-full h-64" @click="goList(item.folderName)">
        <GalleryCard
          :id="item.id"
          :src="item.pathUrl"
          :folderName="item.folderName"
        >
        </GalleryCard>
        <GalleryCard
          :id="item.id"
          :src="item.pathUrl"
          :folderName="item.folderName"
        >
        </GalleryCard>
        <GalleryCard
          :id="item.id"
          :src="item.pathUrl"
          :folderName="item.folderName"
        >
        </GalleryCard>
      </div>
      <p class="mt-2 ml-4">{{ item.folderName }}</p>
    </template>
  </GalleryFolderGrid>
</template>

<!-- folderFirstItem : id 폴더명 첫번째사진
folders : id 폴더명 -->

<script setup>
import AppDropdown from '@/components/app/AppDropdown.vue';
import GalleryFolderGrid from '@/components/gallery/GalleryFolderGrid.vue';
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { getGalleryFolderName, getGalleryFolderList } from '@/utils/api/albums';
import Swal from 'sweetalert2';
import { createFolder } from '@/utils/api/albums';
import { useGalleryStore } from '@/stores/gallery.js';
import GalleryCard from '@/components/gallery/GalleryCard.vue';
const router = useRouter();
const galleryStore = useGalleryStore();

// {{ item[0].pathUrl }}
// {{ item[0].galleryCategory.category }}

// 조회 관련 파라미터(폴더별)
const params2 = ref({
  _limit: 1, // 몇개씩 조회
  _page: 1, // 현재 페이지를 조회
  _sort: 'id', // 무엇을
  _order: 'desc', // 내림차순
  categoryName: '', // 조회할 폴더 이름
});

// 폴더 생성
const folderCreate = async () => {
  const { value: title } = await Swal.fire({
    title: '폴더 이름 작성',
    input: 'text',
    inputPlaceholder: '추가할 폴더 이름을 작성해주세요.',
    showCancelButton: true,
  });
  if (title) {
    Swal.fire('Saved!', '', 'success');
    try {
      console.log(title);
      await createFolder({ category: title });
      // 새로고침 해야 추가되는 현상있음
      // 조회 박으면 해결될 것
      fetchFolders();
      router.push({ name: 'GalleryFolder' });
    } catch (error) {
      console.log(error);
    }
  }
};

// 폴더 리스트
const folders = ref([]);
const coupleId = ref(1);
const folderFirstItem = ref([]);

// 폴더 리스트 가져오기
const fetchFolders = async () => {
  try {
    const { data } = await getGalleryFolderName(coupleId.value);
    folders.value = data;

    for (const folder of data) {
      try {
        params2.value.categoryName = folder.name;
        const { data: data2 } = await getGalleryFolderList(
          coupleId.value,
          params2.value,
        );
        let data3 = {};
        if (data2.length === 0) {
          console.log('데이터가 없습니다.');
          data3 = {
            folderName: folder.name,
            pathUrl:
              'https://daisyui.com/images/stock/photo-1606107557195-0e29a4b5b4aa.jpg',
          };
        } else {
          data3 = {
            folderName: folder.name,
            pathUrl: data2[0].pathUrl,
          };
        }
        folderFirstItem.value.push(data3);
      } catch (err) {
        console.error(err);
      }
      console.log(folderFirstItem.value);
    }
  } catch (err) {
    console.error(err);
  }
};

onMounted(() => {
  fetchFolders();
  galleryStore.addFolderNameList(coupleId.value);
});

const totalView = () => {
  router.push({
    name: 'GalleryList',
    params: {
      folderName: '전체보기',
    },
  });
};

// 해당 폴더의 사진 리스트 보러가기
const goList = folderName => {
  router.push({
    name: 'GalleryList',
    params: {
      folderName,
    },
  });
};
</script>

<style lang="scss" scoped></style>
