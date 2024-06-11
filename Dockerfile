#FROM ubuntu:latest
#LABEL authors="Nurgissa"
#
#ENTRYPOINT ["top", "-b"]

FROM openjdk:17-jdk

WORKDIR /app

COPY target/volunteer-back-0.0.1-SNAPSHOT.jar /app/volunteer-back.jar

EXPOSE 8080

CMD ["java", "-jar", "volunteer-back.jar"]