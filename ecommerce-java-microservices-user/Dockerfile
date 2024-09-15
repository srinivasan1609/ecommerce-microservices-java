FROM openjdk:21-slim
MAINTAINER srinivasan
WORKDIR /ecommerce-java-microservices-user
CMD ["./gradlew", "clean", "bootJar"]
COPY build/libs/*.jar app.jar

EXPOSE 5001
ENTRYPOINT ["java", "-jar", "app.jar"]