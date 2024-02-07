# base 이미지 설정
FROM amazoncorretto:17

# jar 파일 위치를 변수로 설정
ARG JAR_FILE=build/libs/*.jar

# 환경변수 설정
#ENV CUSTOM_NAME longD-BE

# jar 파일을 컨테이너 내부에 복사
COPY ${JAR_FILE} main-longDapp.jar

# 외부 호스트 8080 포트로 노출(외부에 호출될 포트)
EXPOSE 3000git pull

# 컨테이너 빌드 시 호스트의 파일을 컨테이너로 복사(local 환경에 있는 프로젝트 내부에 만들어진 jar파일을 컨테이너로 복사한다)
#ADD build/*.jar longDapp.jar

# 실행 명령어
ENTRYPOINT ["java","-jar","/main-longDapp.jar"]

#CMD ["java", "-Dtest.customName=${CUSTOM_NAME}", "-jar", "app.jar"]
