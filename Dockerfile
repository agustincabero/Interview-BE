# --- Etapa 1: Build ---
# Usamos una imagen de Maven con Java 21 para compilar el proyecto
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copiamos el pom.xml y descargamos las dependencias (para aprovechar la caché de Docker)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamos el código fuente y generamos el .jar saltando los tests para ganar tiempo
COPY src ./src
RUN mvn clean package -DskipTests

# --- Etapa 2: Run ---
# Usamos una imagen ligera de Java 21 para ejecutar la app
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copiamos el .jar generado en la etapa anterior
# Nota: Asegúrate de que el nombre del .jar coincida o usa un comodín como *.jar
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto 8080 (el estándar de Spring Boot)
EXPOSE 8080

# Comando para arrancar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]