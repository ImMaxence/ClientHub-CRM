# Dockerfile pour ClientHub CRM Backend (Spring Boot)
FROM eclipse-temurin:17-jdk

# Répertoire de travail
WORKDIR /app

# Copie du jar compilé
COPY target/clienthub-0.0.1-SNAPSHOT.jar app.jar

# Variables d'environnement (personnalisez au besoin)
ENV SPRING_PROFILES_ACTIVE=prod
ENV JAVA_OPTS=""

# Port exposé
EXPOSE 8080

# Commande de lancement
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
