# birra-app-back
Trabajo para Sistemas de Información Gerencial


Para correr simplemente hacer

$ ./mvnw install dockerfile:build

$ docker run -e "SPRING_PROFILES_ACTIVE=dev" -p 8080:8080 -t springio/birraapp-backend 
