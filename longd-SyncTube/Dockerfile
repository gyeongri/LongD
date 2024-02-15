FROM haxe:4.3.0-alpine3.15
# 작업 디렉토리 설정
WORKDIR /usr/src/app

RUN apk add nodejs npm git

# 소스 코드 복사
COPY . .
# COPY build ./build
COPY res ./res
COPY src ./src
COPY user ./user
COPY build-*.hxml ./
COPY package*.json ./
COPY default-config.json ./

# RUN npm install
RUN npm install ws

# 의존성 설치
# RUN npm install

# RUN npm ci; \
#     haxelib install all --always; \
#     haxe build-all.hxml
RUN npm run dev
EXPOSE 4200
# 서버 실행 명령 추가
CMD ["node", "build/server.js"]

# CMD npm start


# # 사용할 베이스 이미지 선택
# #FROM node:latest
# FROM node:20.10.0 AS build

# # 작업 디렉토리 설정
# WORKDIR /app

# # 프로젝트 종속성 설치
# COPY package*.json ./
# RUN npm install

# # 소스 코드 복사
# COPY . .

# # 애플리케이션 빌드
# RUN npm run build

# # 서버 실행
# CMD ["node", "build/server.js"]


# # Node.js 20.10.0 버전을 기반으로 한 이미지 사용
# FROM node:20.10.0 AS build

# # 작업 디렉토리 설정
# WORKDIR /app

# # 소스 코드 복사
# COPY . .

# # npm install을 하기 전에 불필요한 파일을 삭제
# RUN rm -rf /app/node_modules /app/package-lock.json

# # 필요한 패키지 설치
# # RUN apt-get update && apt-get install -y npm

# # 필요한 패키지 설치
# # RUN npm install -g npm@latest

# # 필요한 패키지 설치
# RUN npm install

# # Vue 프로젝트 빌드
# # RUN npm run build

# # Nginx 사용
# FROM nginx:latest

# # 작업 디렉토리 설정
# # WORKDIR /usr/share/nginx/html

# # Vue 프로젝트 빌드 결과물을 복사
# # COPY --from=build /app/dist .

# # 4200번 포트 열기
# EXPOSE 4200

# COPY sync-custom.conf /etc/nginx/sites-enabled/
# CMD ["nginx", "-g", "daemon off;"] && npm start
################################################################

# CMD ["nginx", "-g", "daemon off; & npm start"]

# start.sh 스크립트를 복사
# COPY start.sh .

# 실행 권한 부여
# RUN chmod +x start.sh

# 컨테이너 실행 시 start.sh 스크립트 실행
# CMD ["./start.sh"]


# # Node.js 20.10.0 버전을 기반으로 한 이미지 사용
# FROM node:20.10.0 AS build

# # 작업 디렉토리 설정
# WORKDIR /app

# # 소스 코드 복사
# COPY . .

# # npm install을 하기 전에 불필요한 파일을 삭제
# RUN rm -rf /app/node_modules /app/package-lock.json

# # 필요한 패키지 설치
# RUN apt-get update && apt-get install -y npm

# # 필요한 패키지 설치
# RUN npm install

# # RUN npm start

# # Vue 프로젝트 빌드
# #RUN npm run build

# # Nginx 사용
# FROM nginx:latest


# # 3001번 포트 열기
# EXPOSE 4200


# CMD npm start
# CMD ["/start.sh"]
# CMD ["npm", "start"]
# COPY start.sh /usr/src/app/
# CMD ["sh", "/usr/src/app/start.sh"]



# #CMD ["npm", "run", "build"]
# CMD ["npm", "start"]
# # Nginx 시작
# CMD ["nginx", "-g", "daemon off;"]


# FROM haxe:4.3.0-alpine3.15
# WORKDIR /usr/src/app

# RUN apk add nodejs npm git

# COPY res ./res
# COPY src ./src
# COPY user ./user
# COPY build-*.hxml ./
# COPY package*.json ./
# COPY default-config.json ./

# RUN npm ci; \
#    haxelib install all --always; \
#    haxe build-all.hxml

# EXPOSE 4200

# CMD npm start
