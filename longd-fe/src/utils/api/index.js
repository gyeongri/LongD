import axios from 'axios';

const { VITE_VUE_API_URL } = import.meta.env;

const api = axios.create({
  withCredentials: true,
  baseURL: VITE_VUE_API_URL, // API의 기본 URL을 여기에 설정합니다.
  headers: {
    'Content-Type': 'application/json',
    // 다른 헤더를 필요에 따라 설정할 수 있습니다.
  },
});

// 가상 서버 키는 법: npm run db
// (터미널 하나 추가 필요)

// 임시(가상의) DB 용(albums)
const api2 = axios.create({
  baseURL: 'http://localhost:5000/albums', // API의 기본 URL을 여기에 설정합니다.
  headers: {
    'Content-Type': 'application/json',
    // 다른 헤더를 필요에 따라 설정할 수 있습니다.
  },
});
// 임시(가상의) DB 용(folders)
const api3 = axios.create({
  baseURL: 'http://localhost:5000/folders', // API의 기본 URL을 여기에 설정합니다.
  headers: {
    'Content-Type': 'application/json',
    // 다른 헤더를 필요에 따라 설정할 수 있습니다.
  },
});

const viduapi = axios.create({
  baseURL: 'https://localhost:5000/recording-java/api',
  headers: {
    'Content-Type': 'application/json',
    Authorization: 'Basic T1BFTlZJRFVBUFA6TVlfU0VDUkVU',
  },
});
<<<<<<< HEAD
export { api, api2, api3, viduapi };
=======
const stompApi = axios.create({
  baseURL: 'http://localhost:8080',
});
export { api, api2, api3, viduapi, stompApi };
>>>>>>> hyunjae
