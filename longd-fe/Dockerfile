# Node.js 20.10.0 버전을 기반으로 한 이미지 사용
FROM node:20.10.0 AS build

# 작업 디렉토리 설정
WORKDIR /app

# 소스 코드 복사
COPY . .

# npm install을 하기 전에 불필요한 파일을 삭제
RUN rm -rf /app/node_modules /app/package-lock.json

# 필요한 패키지 설치
RUN npm install

# Vue 프로젝트 빌드
RUN npm run build

# Nginx 사용
FROM nginx:latest

COPY fe-custum.conf /etc/nginx/conf.d

# Nginx 설정을 위한 별도의 디렉토리 생성
RUN mkdir -p /usr/share/nginx/html

# 3001번 포트 열기
EXPOSE 3001

# #CMD ["npm", "run", "build"]
#  CMD ["npm", "run", "build"]

# Nginx 시작
CMD ["nginx", "-g", "daemon off;"]