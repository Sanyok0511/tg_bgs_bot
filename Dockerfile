FROM maven:latest AS builder
WORKDIR /opt/builder/elite/
COPY pom.xml pom.xml
RUN mvn dependency:go-offline
COPY ./src ./src
RUN mvn clean package
################################################################################
FROM eclipse-temurin:21
WORKDIR /opt/elitebgs
COPY --from=builder /opt/builder/elite/target/EliteBgsTgBot-1.0-SNAPSHOT-spring-boot.jar /opt/elitebgs/app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/opt/elitebgs/app.jar"]