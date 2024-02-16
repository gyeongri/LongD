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
EXPOSE 4200

# 서버 실행 명령 추가
CMD ["node", "build/server.js"]
