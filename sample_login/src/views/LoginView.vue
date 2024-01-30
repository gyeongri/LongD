<template>


    <div>
        <button @click="loginStart">(누르면 로그인 시작할거니까 카카오 로고 이미지가 있으면 좋겠는 부분)</button>
        test
    </div>
    
    </template>
    
    <script>
    
    import axios from 'axios';
    
    export default {
      methods: {
        loginStart() {
          
          // Axios를 사용하여 HTTP GET 요청을 보냅니다
          const headers = {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer Q9h6F-cxgwtjgMpr7rYVLkKRgz9gP83djhkKKiUPAAABjSB875pONYg--5I0Sw', // 여기에 실제 액세스 토큰을 넣어주세요
          };
    
          // Axios를 사용하여 HTTP GET 요청을 보냅니다
          axios.get('https://kapi.kakao.com/v2/user/me', { headers })
            .then(response => {
              // 요청이 성공한 경우
              console.log('Response:', response.data);
            })
            .catch(error => {
              // 요청이 실패한 경우
              console.error('Error:', error);
            });
        },
      },
    };
    
    
    </script>
    
    <style>
    
    </style>