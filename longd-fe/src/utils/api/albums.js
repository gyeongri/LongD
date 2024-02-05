import { api2 } from '.';

// 여기도 folderName을 추가해주면 됨!!
export function getAlbums(params) {
  return api2.get('/', { params });
}

export function getAlbumById(id) {
  return api2.get(`/${id}`);
}

export function createAlbum(data) {
  return api2.post('', data);
}

// 일부 수정
export function updateAlbum(id, data) {
  return api2.patch(`/${id}`, data);
}

export function deleteAlbum(id) {
  return api2.delete(`/${id}`);
}
