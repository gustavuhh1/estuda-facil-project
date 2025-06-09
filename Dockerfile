# Etapa 1: Build da aplicação
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

# Etapa 2: Executar aplicação
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copia o JAR da etapa anterior
COPY --from=build /app/target/estuda-facil-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]
