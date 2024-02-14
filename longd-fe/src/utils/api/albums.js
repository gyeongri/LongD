import { api } from '.';

// 여기도 folderName을 추가해주면 됨!!
export function getAlbums(params) {
  return api.get('/', { params });
}
// 현재 커플의 갤러리 폴더 이름 리스트를 DB에서 불러옵니다.
export function getGalleryFolderName(coupleId, params) {
  return api.get(`/galleryCategory/getList/${coupleId}`, { params });
}

// 현재 커플의 갤러리 전체를 DB에서 불러옵니다.
export function getGalleryTotalList(coupleId, params) {
  return api.get(`/gallery/getList/${coupleId}`, { params });
}

// 현재 커플의 해당 폴더 갤러리 전체를 DB에서 불러옵니다.
export function getGalleryFolderList(coupleId, params) {
  return api.get(`/gallery/getList/${coupleId}`, { params });
}

export function getGalleryDetail(id) {
  return api.get(`/gallery/get/${id}`);
}

// 갤러리 DB에 폴더 값을 저장합니다, 로그인한 회원의 coupleId가 같이 입력됩니다.
export function createFolder(data) {
  return api.post('/galleryCategory/add', data);
}

// 갤러리 DB에 값을 저장합니다, 로그인한 회원의 coupleId가 같이 입력됩니다.
export function createGallery(data) {
  return api.post('/gallery/add', data);
}

// 갤러리 DB에 값을 수정합니다, 수정하려는 데이터의 coupleId값과, 로그인된 회원의 coupleId가 같아야 입력됩니다.
// parameter는 수정 될 수 있습니다.

export function updateGallery(id, data) {
  return api.patch(`/gallery/modify/${id}`, data);
}

// 갤러리 DB에 값을 삭제합니다, 삭제하려는 데이터의 coupleId값과, 로그인된 회원의 coupleId가 같아야 입력됩니다.
// parameter는 수정 될 수 있습니다.

export function deleteGallery(data) {
  return api.post('/gallery/delete', data);
}

//비디오만 받아오기
export function getVideo() {
  return api.get('gallery/getMovieList');
}
