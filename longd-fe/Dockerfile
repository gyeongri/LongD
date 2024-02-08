
# Node.js 버전 지정
FROM node:20.10.0 AS build

# 작업 디렉토리 설정
WORKDIR /app

# nginx 파일 위치를 변수로 설정
#ARG NGINX_FILE=

# nginx 설정파일
# ADD longd-fe/nginx/* /usr/share/nginx/conf

# 소스 코드 복사
COPY . .

# npm install을 하기 전에 불필요한 파일을 삭제
RUN rm -rf /app/node_modules /app/package-lock.json

# 필요한 패키지 설치
RUN npm install

# Vue 프로젝트 빌드
RUN npm run build

# Nginx를 이용하여 정적 파일 서빙
FROM nginx:latest

# Nginx 설정 파일 복사
COPY /home/nginx /usr/share/nginx

# 빌드된 애플리케이션을 Nginx에 복사
COPY --from=build /app/dist /usr/share/nginx/html

# 80번 포트 열기
EXPOSE 80

# Nginx 실행
CMD ["nginx", "-g", "daemon off;"]

