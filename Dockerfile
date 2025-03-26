# Utilisez l'image officielle OpenJDK 21
FROM openjdk:21-jdk-slim

# Ajoutez le fichier JAR de votre application (généralement dans le répertoire /target après un build Maven ou Gradle)
ARG JAR_FILE=target/productCriteria-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Exposez le port sur lequel l'application Spring Boot fonctionne (par défaut 8080)
EXPOSE 8081

# Commande pour démarrer l'application Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
