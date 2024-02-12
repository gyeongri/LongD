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

function sendinfo(payload, success, fail) {
  userApi.post(`/user/add`, JSON.stringify(payload)).then(success).catch(fail);
}

// 프로필 나라 도시 데이터 들고오기
function getNationList(success, fail) {
  userApi.get('/user/getNationList').then(success).catch(fail);
}

function partnerinfo(success, fail) {
  userApi.get(`/couplelist/partnerInfo/get`).then(success).catch(fail);
}

function BaseInfo(success, fail) {
  userApi.get(`/user/registInfo`).then(success).catch(fail);
}

function logout(success, fail) {
  userApi.post(`/user/customlogout`).then(success).catch(fail);
}

// 호출시 로그인 상태를 확인합니다.
// 미 로그인시, "롱디에 로그인 되어 있지 않음" 상태 반환, 로그인 되어있을경우 회원정보 객체 반환
function loginstate(success, fail) {
  userApi.get('/user/state').then(success).catch(fail);
}

function coupleMatching(payload, success, fail) {
  userApi.post(`/couplelist/add`, payload).then(success).catch(fail);
}

function coupleDataGet(success, fail) {
  userApi.get(`/couplelist/get`).then(success).catch(fail);
}

function coupleDataModify(payload, success, fail) {
  userApi.post(`/couplelist/modify`, payload).then(success).catch(fail);
}
// 화면잠금 비밀번호 초기화
function resetClosedPasswords(success, fail) {
  userApi.get(`/user/resetSimplePassWord`).then(success).catch(fail);
}
function checkSimplePassword(payload, success, fail) {
  userApi.post(`/user/unlock`, payload).then(success).catch(fail);
}
export {
  userApi,
  sendinfo,
  getNationList,
  partnerinfo,
  BaseInfo,
  logout,
  loginstate,
  coupleMatching,
  coupleDataGet,
  coupleDataModify,
  resetClosedPasswords,
  checkSimplePassword,
};
