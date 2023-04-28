FROM maven:3.8.6-openjdk-18 as build
LABEL authors="jhgrzybowski"

ENV HOME=/app
WORKDIR $HOME
ADD . $HOME
RUN --mount=type=cache,target=/root/.m2 mvn -f $HOME/pom.xml clean package -Dmaven.test.skip

FROM openjdk:18
COPY --from=build /app/target/nbpapi-0.0.1-SNAPSHOT.jar /app/runner.jar
ENTRYPOINT java -jar /app/runner.jar