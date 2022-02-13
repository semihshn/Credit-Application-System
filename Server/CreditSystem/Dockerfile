FROM maven:3.8-openjdk-17

ADD ./target/CreditSystem-0.0.1-SNAPSHOT.jar /CreditSystem.jar

ENTRYPOINT ["java", "-jar", "CreditSystem.jar"]

EXPOSE 8080