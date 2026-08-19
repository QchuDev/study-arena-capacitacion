# --- Stage 1: Build ---
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copiar archivos de Gradle (cacheo de dependencias)
COPY gradlew settings.gradle build.gradle ./
COPY gradle ./gradle

# Dar permisos de ejecución al wrapper
RUN chmod +x gradlew

# Descargar dependencias (se cachea mientras no cambien build.gradle/settings.gradle)
RUN ./gradlew dependencies --no-daemon

# Copiar código fuente
COPY src ./src

# Compilar el JAR sin correr tests
RUN ./gradlew bootJar --no-daemon -x test





# --- Stage 2: Run ---
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copiar el JAR generado desde el stage de build
COPY --from=build /app/build/libs/*.jar app.jar

# Puerto de la app
EXPOSE 8080

# Arrancar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
