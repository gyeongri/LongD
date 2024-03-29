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

// 맨 처음 저장할 때
function postPlanAllData(planAll, success, fail) {
  userApi.post(`/plan/info/add/allInfo`, planAll).then(success).catch(fail);
}

// 여행 일정 전체 목록 불러오기(plan 테이블 전체)
function getPlanList(coupleList, success, fail) {
  userApi.get(`/plan/get/${coupleList}`).then(success).catch(fail);
}

// 여행 정보 불러오기
function getPlan(planId, success, fail) {
  userApi.get(`/plan/get/detail/${planId}`).then(success).catch(fail);
}

// 여행 개별 일정 중 PlanInfo 데이터만 불러오기(plan 테이블 내 하나 PlanInfo)
function getPlanDetail(planId, success, fail) {
  userApi.get(`/plan/info/getList/${planId}`).then(success).catch(fail);
}

// 여행 개별 일정 수정 후 보내기(plan 테이블 내 하나)
function postPlanData(planDataPart, success, fail) {
  userApi.post(`/plan/add`, planDataPart).then(success).catch(fail);
}

// 여행 개별 일정 삭제(plan 테이블 내 하나)
function deletePlanData(planId, success, fail) {
  userApi.delete(`/plan/delete/${planId}`).then(success).catch(fail);
}

// 장소 삭제(일정 중 장소 삭제)
function delectPlanInfoData(planInfoId, success, fail) {
  userApi.delete(`/plan/info/delete/${planInfoId}`).then(success).catch(fail);
}

// 플랜 ID기준으로 사진 불러오기
function getGalleryWithPlanID(planId, success, fail) {
  userApi.get(`/gallery/getList/plan/${planId}`).then(success).catch(fail);
}

// 고른 사진 플랜id값 빼주기
function deletePlanIDinpicture(pictureIds, success, fail) {
  userApi
    .post(`/gallery/modify/deletePlanId`, pictureIds)
    .then(success)
    .catch(fail);
}

export {
  postPlanAllData,
  getPlanList,
  getPlanDetail,
  postPlanData,
  deletePlanData,
  delectPlanInfoData,
  getPlan,
  getGalleryWithPlanID,
  deletePlanIDinpicture,
};
