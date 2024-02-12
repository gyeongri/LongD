
FROM maven:3.6.3 as build
WORKDIR /openvidu-recording-java
COPY ./pom.xml pom.xml
COPY ./src/main src/main

RUN mvn -e -X clean install
RUN mvn -o package

FROM alpine:3.11

RUN apk update && \
  apk add openjdk11-jre && \
  rm -rf /var/cache/apk/*

# Install basic-webinar
RUN mkdir -p /opt/openvidu-recording-java
COPY --from=build /openvidu-recording-java/target/openvidu-recording-java-*.jar /opt/openvidu-recording-java/openvidu-recording-java.jar
# Entrypoint
COPY ./docker/entrypoint.sh /usr/local/bin
RUN chmod +x /usr/local/bin/entrypoint.sh

CMD /usr/local/bin/entrypoint.sh

## base 이미지 설정
#FROM amazoncorretto:17
#
## jar 파일 위치를 변수로 설정
#ARG JAR_FILE=lib/*.jar
#
## jar 파일을 컨테이너 내부에 복사
#COPY ${JAR_FILE} openvidu-longDapp.jar
#
## 외부 호스트 3003 포트로 노출(외부에 호출될 포트)
#EXPOSE 4443
#
## 실행 명령어
#ENTRYPOINT ["java","-jar","/openvidu-longDapp.jar"]
