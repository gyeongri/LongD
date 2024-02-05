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

// const sendinfo2 = function (param) {
//   return userApi.post('/user/modify', JSON.stringify(param));
// };
// sendinfo2.then(success).catch(error => {
//   console.error(error);
//   throw error;
// });
function sendinfo(param, success, fail) {
  userApi.post(`/user/add`, JSON.stringify(param)).then(success).catch(fail);
}

function partnerinfo(success, fail) {
  userApi.get(`/couplelist/partner/get`).then(success).catch(fail);
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

export { userApi, sendinfo, partnerinfo, BaseInfo, logout, loginstate };
