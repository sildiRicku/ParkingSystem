FROM openjdk:17
EXPOSE 8080
ENV SPRING_PROFILES_ACTIVE=dev
ADD target/spring-boot-docker.jar spring-boot-docker.jar
ENTRYPOINT ["java", "-jar", "/spring-boot-docker.jar"]
