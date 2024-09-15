FROM openjdk:21-slim
MAINTAINER srinivasan
WORKDIR /ecommerce-products
CMD ["./gradlew", "clean", "bootJar"]
COPY build/libs/*.jar app.jar

EXPOSE 5002
ENTRYPOINT ["java", "-jar", "app.jar"]