FROM openjdk:17
VOLUME /tmp
EXPOSE 8082
ARG JAR_FILE=target/art-tracker-back.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]