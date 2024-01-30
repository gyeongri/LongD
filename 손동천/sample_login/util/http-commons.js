import axios from "axios";

const { VITE_VUE_API_URL } = import.meta.env;

// local vue api axios instance
function localAxios() {
  const instance = axios.create({
    withCredentials: true,
    baseURL: VITE_VUE_API_URL,
    headers: {
      'Access-Control-Allow-Origin': 'http://localhost:5173',
      "Content-Type": "application/json;charset=utf-8",
    },
  });
  return instance;
}

export { localAxios };
