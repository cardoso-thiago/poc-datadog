FROM openjdk:17-jdk-slim
WORKDIR /app

COPY build/libs/datadog-tracing-metrics-0.0.1-SNAPSHOT.jar app.jar
COPY dd-java-agent.jar dd-java-agent.jar

EXPOSE 8080
ENTRYPOINT ["java", "-javaagent:/app/dd-java-agent.jar", "-Ddd.logs.injection=true", "-Ddd.trace.sample.rate=1", "-Ddd.service=datadog-tracing-metrics", "-Ddd.env=dev", "-Ddd.trace.128.bit.traceid.logging.enabled=true", "-jar", "app.jar"]