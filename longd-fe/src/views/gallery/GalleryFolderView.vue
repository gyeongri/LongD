<template>
  <div class="flex justify-end mb-10 mr-10">
    <div class="flex items-center space-x-4 z-50">
      <AppDropdown>
        <template v-slot>
          <li class="font-bold" @click="folderCreate"><a>폴더 생성</a></li>
          <li class="font-bold" @click="folderDelete"><a>폴더 삭제</a></li>
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
      <p class="mt-2 ml-4 font-bold">{{ item.folderName }}</p>
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
import { useUserStore } from '@/stores/user';
const router = useRouter();
const galleryStore = useGalleryStore();
const userStore = useUserStore();
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

// 폴더 리스트
const folders = ref([]);
const coupleId = ref();
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
              'https://i.pinimg.com/564x/b4/bd/75/b4bd756f0d38cf1589b6a9ebc8b5fc32.jpg',
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

// 폴더 생성
const folderCreate = async () => {
  const { value: title } = await Swal.fire({
    title: '폴더 이름 작성',
    input: 'text',
    inputPlaceholder: '추가할 폴더 이름을 작성해주세요.',
    showCancelButton: true,
    confirmButtonColor: '#FF9CBD',
    cancelButtonColor: '#a0a0a0',
  });
  if (title) {
    Swal.fire('Saved!', '', 'success');
    try {
      console.log(title);
      await createFolder({ category: title });
      // 새로고침 해야 추가되는 현상있음
      // 조회 박으면 해결될 것
      fetchFolders();
    } catch (error) {
      console.log(error);
    }
  }
};

// 폴더 삭제
const folderDelete = async () => {
  const { value: folder } = await Swal.fire({
    title: '삭제할 폴더를 선택해주세요.',
    input: 'select',
    inputOptions: Object.fromEntries(
      folders.value.map(folder => [folder.name.toLowerCase(), folder.name]),
    ),
    inputPlaceholder: 'Select a folder',
    showCancelButton: true,
    confirmButtonColor: '#FF9CBD',
    cancelButtonColor: '#a0a0a0',
  });
  if (folder) {
    Swal.fire(`당신이 선택한 폴더는 ${folder} 입니다.`); // 삭제 모양으로 만들기
    try {
      // await deleteFolder(형식 맞추기);
      fetchFolders();
    } catch (error) {
      console.error(error);
    }
  }
};

onMounted(() => {
  coupleId.value = userStore.getUserState?.coupleListId;
  setTimeout(() => {
    fetchFolders();
  }, 300);

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
