FROM amazoncorretto:17
COPY /target/*.jar chess.jar
ENTRYPOINT ["java","-jar","/chess.jar"]