# POC Datadog

## Rodando a aplicação

Na raiz da pasta datadog-tracing-metrics:

- `gradle clean build`
- `docker build -t datadog-tracing-metrics:0.0.1 .`
- `DD_API_KEY=API_KEY docker-compose up`

## Dependências para envio de métricas customizadas

```groovy
runtimeOnly 'io.micrometer:micrometer-registry-datadog'
implementation 'org.springframework.boot:spring-boot-starter-actuator'
```

## Dependência para customização do trace com o OpenTelemetry

```groovy
implementation 'io.opentelemetry.instrumentation:opentelemetry-instrumentation-annotations:2.5.0'
```

## Dependência para customização do trace com o DD API

```groovy
implementation 'com.datadoghq:dd-trace-api:1.37.0'
```
