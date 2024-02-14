import axios from 'axios';

const { VITE_VUE_API_URL, VITE_BASE_IP } = import.meta.env;

const userApi = axios.create({
  withCredentials: true,
  baseURL: VITE_VUE_API_URL,
  headers: {
    'Access-Control-Allow-Origin': VITE_BASE_IP,
    'Content-Type': 'application/json;charset=utf-8',
  },
});

// 소셜로그인 정보 가져오기
function BaseInfo(success, fail) {
  userApi.get(`/user/registInfo`).then(success).catch(fail);
}

// 회원정보 수정
function sendinfo(payload, success, fail) {
  userApi.post(`/user/add`, JSON.stringify(payload)).then(success).catch(fail);
}

// 프로필 나라 도시 데이터 가져오기
function getNationList(success, fail) {
  userApi.get('/user/getNationList').then(success).catch(fail);
}

// 상대정보 가져오기
function partnerinfo(success, fail) {
  userApi.get(`/couplelist/partnerInfo/get`).then(success).catch(fail);
}

// 상대와 연결끊기
function BrokenLove(success, fail) {
  userApi.delete(`/user/disconnect`).then(success).catch(fail);
}

// 로그아웃
function logout(success, fail) {
  userApi.post(`/user/customlogout`).then(success).catch(fail);
}

// 회원탈퇴
function DeleteUser(success, fail) {
  userApi.delete(`/user/delete`).then(success).catch(fail);
}

// 로그인 상태 확인
// 미 로그인시, "롱디에 로그인 되어 있지 않음" 상태 반환, 로그인 되어있을경우 회원정보 객체 반환
function loginstate(success, fail) {
  userApi.get('/user/state').then(success).catch(fail);
}

// 커플매칭
function coupleMatching(payload, success, fail) {
  userApi.post(`/couplelist/add`, payload).then(success).catch(fail);
}

// 커플정보 가져오기
function coupleDataGet(success, success2, fail) {
  userApi.get(`/couplelist/get`).then(success).then(success2).catch(fail);
}

// 커플정보 수정
function coupleDataModify(payload, success, fail) {
  userApi.post(`/couplelist/modify`, payload).then(success).catch(fail);
}

// 화면잠금 비밀번호 초기화
function resetClosedPasswords(success, fail) {
  userApi.get(`/user/resetSimplePassWord`).then(success).catch(fail);
}

// 화면잠금 비밀번호 확인
function checkSimplePassword(payload, success, fail) {
  userApi.post(`/user/unlock`, payload).then(success).catch(fail);
}

export {
  userApi,
  BaseInfo,
  sendinfo,
  getNationList,
  partnerinfo,
  BrokenLove,
  logout,
  DeleteUser,
  loginstate,
  coupleMatching,
  coupleDataGet,
  coupleDataModify,
  resetClosedPasswords,
  checkSimplePassword,
};
