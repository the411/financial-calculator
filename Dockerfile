FROM openjdk:16-jdk
EXPOSE 8080
ADD build/libs/var-0.0.1-SNAPSHOT.jar financial-calculator
ENTRYPOINT ["java", "-jar", "financial-calculator"]