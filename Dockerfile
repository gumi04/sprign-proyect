# Utiliza una imagen base con Java preinstalado
FROM adoptopenjdk:11-jre-hotspot

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR compilado de tu aplicación Spring Boot al contenedor
COPY target/coppel-api.jar app.jar

# Expone el puerto en el que la aplicación Spring Boot escucha
EXPOSE 8080

# Comando para ejecutar la aplicación Spring Boot
CMD ["java", "-jar", "app.jar"]
