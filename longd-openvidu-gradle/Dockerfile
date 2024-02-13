# base 이미지 설정
FROM amazoncorretto:17

# jar 파일 위치를 변수로 설정
ARG JAR_FILE=lib/*.jar

# jar 파일을 컨테이너 내부에 복사
COPY ${JAR_FILE} openvidu-longDapp.jar

# 외부 호스트 3003 포트로 노출(외부에 호출될 포트)
EXPOSE 3003

# 실행 명령어
ENTRYPOINT ["java","-jar","/openvidu-longDapp.jar"]
