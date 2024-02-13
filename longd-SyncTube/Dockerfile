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
#RUN npm run build

# 3001번 포트 열기
EXPOSE 4200

#CMD ["npm", "run", "build"]
CMD ["npm", "start"]


#FROM haxe:4.3.0-alpine3.15
#WORKDIR /usr/src/app
#
#RUN apk add --update nodejs npm git && \
#    npm install -g yarn
#
#COPY res ./res
#COPY src ./src
#COPY user ./user
#COPY build-*.hxml ./
#COPY package*.json ./
#COPY default-config.json ./
#
#RUN npm ci && \
#    haxelib install all --always && \
#    haxe build-all.hxml
#
#EXPOSE 4200
#
#CMD npm start






#FROM haxe:4.3.0-alpine3.15
#WORKDIR /usr/src/app
#
#RUN apk add nodejs npm git
#
#COPY res ./res
#COPY src ./src
#COPY user ./user
#COPY build-*.hxml ./
#COPY package*.json ./
#COPY default-config.json ./
#
#RUN npm ci; \
#    haxelib install all --always; \
#    haxe build-all.hxml
#
#EXPOSE 4200
#
#CMD npm start
