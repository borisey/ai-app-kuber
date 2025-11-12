# Используем JDK 21 (совместим с Spring Boot 3.x)
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# Копируем JAR
COPY target/*.jar app.jar

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "app.jar"]