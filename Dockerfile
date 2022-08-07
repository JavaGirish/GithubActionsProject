FROM maven:3.6.0-jdk-8-alpine

COPY src /home/GithubProject/src

COPY pom.xml /home/GithubProject

COPY testng.xml /home/GithubProject

RUN mvn -f /home/GithubProject/pom.xml clean test -DskipTests=true