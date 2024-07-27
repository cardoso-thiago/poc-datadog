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

## Considerações

- Alterar o nome do campo do `dd.trace_id` no MDC faz a correlação se perder na plataforma em todos os casos
- Em relação à configuração `-Ddd.trace.128.bit.traceid.logging.enabled=true` tanto a informação injetada no log pelo agent como a chamada para o `CorrelationIdentifier.getTraceId()` retornam a informação no formato OTEL que corresponde ao exibido na plataforma.
  - Issue relacionada para Python: https://github.com/DataDog/dd-trace-py/issues/7883
- Mudar as configurações via SystemProperty para alterar o comportamento do agent não surtem efeito. Existe uma maneira de fazer isso em tempo de execução?
- A configuração `-Ddd.logs.injection=true` de fato é necessária para injeção das informações de traceId e spanId no log