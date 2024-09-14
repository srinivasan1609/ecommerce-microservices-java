FROM openjdk:21-slim
MAINTAINER srinivasan
WORKDIR /ecommerce-service-registry
CMD ["./gradlew", "clean", "bootJar"]
COPY ecommerce-service-registry/build/libs/*.jar app.jar

EXPOSE 5000
ENTRYPOINT ["java", "-jar", "app.jar"]