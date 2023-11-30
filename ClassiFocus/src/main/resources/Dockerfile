FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} classifocus.jar
ENTRYPOINT ["java","-jar","/classifocus.jar"]