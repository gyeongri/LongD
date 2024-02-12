import axios from 'axios';

const { VITE_VUE_API_URL, VITE_BASE_IP } = import.meta.env;

const instance = axios.create({
  withCredentials: true,
  baseURL: VITE_VUE_API_URL,
  headers: {
    'Access-Control-Allow-Origin': VITE_BASE_IP,
    'Content-Type': 'multipart/form-data',
  },
});

function uploadImage(param, success, success2, fail) {
  instance
    .post(`/fileUpload/uploads`, param)
    .then(success)
    .then(success2)
    .catch(fail);
}

export { instance, uploadImage };
