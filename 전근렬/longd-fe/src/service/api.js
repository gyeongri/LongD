import axios from 'axios';

const api = axios.create({
  baseURL: 'localhost:3080', // API의 기본 URL을 여기에 설정합니다.
  headers: {
    'Content-Type': 'application/json',
    // 다른 헤더를 필요에 따라 설정할 수 있습니다.
  },
});

export default api;
