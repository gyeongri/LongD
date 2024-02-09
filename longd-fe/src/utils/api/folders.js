import { api3 } from '.';

export function getFolders(params) {
  return api3.get('/', { params });
}

export function getFolderById(id) {
  return api3.get(`/${id}`);
}

export function createFolder(data) {
  return api3.post('', data);
}

// 일부 수정
export function updateFolder(id, data) {
  return api3.patch(`/${id}`, data);
}

export function deleteFolder(id) {
  return api3.delete(`/${id}`);
}
