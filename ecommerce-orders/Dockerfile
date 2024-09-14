FROM openjdk:21-slim
MAINTAINER srinivasan
WORKDIR /ecommerce-orders
CMD ["./gradlew", "clean", "bootJar"]
COPY ecommerce-orders/build/libs/*.jar app.jar

EXPOSE 5002
ENTRYPOINT ["java", "-jar", "app.jar"]