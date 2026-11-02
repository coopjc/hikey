FROM eclipse-temurin:25-jdk-jammy AS builder

WORKDIR /app

COPY mvnw pom.xml ./

COPY .mvn .mvn

RUN chmod +x mvnw && ./mvnw dependency:go-offline -B

COPY src src

RUN ./mvnw package -DskipTests -B

FROM eclipse-temurin:21-jre-jammy as runner

WORKDIR /app

RUN addgroup --system hikey && adduser --system app --ingroup hikey

COPY --from=builder /app/target/*.jar app.jar

USER app:hikey

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]