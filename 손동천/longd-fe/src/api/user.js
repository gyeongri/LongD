import { api } from '.';

//로그인 상태를 확인합니다.
//미 로그인시 "로그인 되어 있지 않음", 로그인 되어있을 시 user 객체가 리턴됩니다.
export function checkloginstate(success, fail) {
  return api
    .get(`/user/state`)
    .then(response => {
      success(response.data);
    })
    .catch(error => {
      fail(error);
    });
}
