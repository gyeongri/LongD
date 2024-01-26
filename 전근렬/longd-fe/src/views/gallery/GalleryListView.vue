<template>
  <div>
    <GalleryFilter
      v-model:id="params.id_like"
      :limit="params._limit"
      @update:limit="changeLimit"
    >
    </GalleryFilter>

    <AppGrid :items="items">
      <template v-slot="{ item }">
        <GalleryCard
          :src="item.src"
          :id="item.id"
          @click="goDetail(item.id)"
        ></GalleryCard>
      </template>
    </AppGrid>
    <AppPagination
      :current-page="params._page"
      :page-count="pageCount"
      @page="page => (params._page = page)"
    />
  </div>
</template>

<script setup>
import GalleryFilter from '@/components/gallery/GalleryFilter.vue';
import GalleryCard from '@/components/gallery/GalleryCard.vue';
import AppPagination from '@/components/app/AppPagination.vue';
import AppGrid from '@/components/app/AppGrid.vue';
import { ref, computed, watchEffect } from 'vue';
import { useRouter } from 'vue-router';
import { getAlbums } from '@/api/albums';
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
</script>

<style lang="scss" scoped></style>
