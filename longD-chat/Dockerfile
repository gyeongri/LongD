# base 이미지 설정
FROM amazoncorretto:17

# jar 파일 위치를 변수로 설정
ARG JAR_FILE=build/libs/*.jar

# jar 파일을 컨테이너 내부에 복사
COPY ${JAR_FILE} chat-longDapp.jar

# 외부 호스트 5000 포트로 노출(외부에 호출될 포트)
EXPOSE 5000

# 실행 명령어
ENTRYPOINT ["java","-jar","/chat-longDapp.jar"]

#CMD ["java", "-Dtest.customName=${CUSTOM_NAME}", "-jar", "app.jar"]s