import { api } from '.';

// 캘린더 DB에 저장된 값을 불러옵니다, 로그인된 회원의 coupleId가 일치하는 정보만 가져옵니다.
export function getCalenderInfo(coupleId) {
  return api.get(`/calender/get/${coupleId}`);
}

// 캘린더 DB에 값을 저장합니다, 로그인한 회원의 coupleId가 같이 입력됩니다.
export function setCalenderInfo(data) {
  return api.post('/calender/add', data);
}

// 캘린더 DB에 값을 수정합니다, 수정하려는 데이터의 coupleId값과, 로그인된 회원의 coupleId가 같아야 입력됩니다.
// parameter는 수정 될 수 있습니다.
export function updateCalenderInfo(id, data) {
  return api.patch(`/calender/modify/${id}`, data);
}
// 캘린더 DB에 값을 삭제합니다, 삭제하려는 데이터의 coupleId값과, 로그인된 회원의 coupleId가 같아야 입력됩니다.
// parameter는 수정 될 수 있습니다.
export function delCalenderInfo(id, data) {
  return api.delete(`/calender/delete/${id}`, data);
}
