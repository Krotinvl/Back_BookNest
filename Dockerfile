FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY build/libs/demo-1.0.0.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]