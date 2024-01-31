import axios from 'axios';

const { VITE_BASE_IP, VITE_VUE_API_URL } = import.meta.env;

const userApi = axios.create({
  withCredentials: true,
  baseURL: VITE_VUE_API_URL,
  headers: {
    'Access-Control-Allow-Origin': VITE_VUE_API_URL,
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
  userApi.post(`/user/modify`, JSON.stringify(param)).then(success).catch(fail);
}

function BaseInfo(success, fail) {
  userApi.get(`/user/registInfo`).then(success).catch(fail);
}

function checkLogin(success, fail) {
  userApi.get(`/user/checklogin`).then(success).catch(fail);
}

export { userApi, sendinfo, BaseInfo, checkLogin };
