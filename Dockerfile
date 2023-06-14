FROM openjdk:17
EXPOSE 8080
WORKDIR /app
COPY target/springboot-bookapp.jar springboot-bookapp.jar
ENTRYPOINT ["java", "-jar", "/app/springboot-bookapp.jar"]