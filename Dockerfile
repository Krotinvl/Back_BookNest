FROM eclipse-temurin:21-jdk as builder

WORKDIR /app

COPY . .

RUN chmod +x gradlew

RUN ./gradlew build -x test

FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=builder /app/build/libs/demo-1.0.0.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]