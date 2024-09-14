FROM openjdk:21-slim
MAINTAINER srinivasan
WORKDIR /ecommerce-java-microservices-user
CMD ["./gradlew", "clean", "bootJar"]
COPY ecommerce-java-microservices-user/build/libs/*.jar app.jar

EXPOSE 5001
ENTRYPOINT ["java", "-jar", "app.jar"]