FROM eclipse-temurin:17-jdk AS builder

WORKDIR /app
COPY . .

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jdk AS runtime-builder

RUN jlink \
    --module-path /opt/java/openjdk/jmods \
    --add-modules java.base,java.logging,java.sql,java.xml,java.desktop \
    --output /custom-jre \
    --compress=2 \
    --no-header-files \
    --no-man-pages

FROM alpine:3.18

WORKDIR /app

COPY --from=runtime-builder /custom-jre /opt/java
COPY --from=builder /app/target/spring-boot-monitoring-demo-0.0.1-SNAPSHOT.jar app.jar

ENV PATH="/opt/java/bin:$PATH"

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]