FROM maven:3.8.4-openjdk-17-slim as builder
WORKDIR /src
COPY . .
RUN mvn clean install -Dmaven.test.skip

FROM openjdk:17-alpine3.14
COPY --from=builder /src/target/credit-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
