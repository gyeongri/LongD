import { defineStore } from 'pinia';
import { ref } from 'vue';
import { getGalleryFolderName } from '@/utils/api/albums';
export const useGalleryStore = defineStore('gallery', () => {
  const folderNameList = ref([]);

  const addFolderNameList = async coupleId => {
    try {
      const { data } = await getGalleryFolderName(coupleId);
      folderNameList.value = data;
    } catch (err) {
      console.error(err);
    }
  };

  return { folderNameList, addFolderNameList };
});
