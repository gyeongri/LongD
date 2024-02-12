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

function getPlanList(coupleList, success, fail) {
  userApi.get(`/plan/get/${coupleList}`).then(success).catch(fail);
}

function getPlanDetail(planId, success, fail) {
  userApi.get(`/plan/info/getList/${planId}`).then(success).catch(fail);
}

export { getPlanList, getPlanDetail };
