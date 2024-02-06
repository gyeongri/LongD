# 사용할 Node.js 버전 지정
FROM node:20.10.0

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

# 빌드된 애플리케이션을 제외한 파일 제거
#RUN rm -rf node_modules

# 애플리케이션 실행을 위한 명령어
CMD ["npm", "run", "dev"]
