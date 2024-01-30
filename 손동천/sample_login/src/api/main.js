import { localAxios } from "../../util/http-commons";

const local = localAxios(); // axios instance

function checkloginstate(success, fail) {
    local.get(`/user/state`).then(success).catch(fail);
}

function login(success, fail) {
    local.get(`/user/info`).then(success).catch(fail);
}

function logout(success, fail) {
    local.post(`/user/customlogout`).then(success).catch(fail);
}

function test(success, fail) {
    local.get(`/user/test`).then(success).catch(fail);
}

function sendinfo(param, success, fail) {
    local.post(`/user/modify`, JSON.stringify(param)).then(success).catch(fail);
}

function userDelete(success, fail) {
    local.get(`/user/delete`).then(success).catch(fail);
}

function BaseInfo(success, fail) {
    local.get(`/user/registInfo`).then(success).catch(fail);
}


export {
    checkloginstate,
    login,
    logout,
    test,
    sendinfo,
    userDelete,
    BaseInfo,
}