# POC Datadog

## Rodando o agent com docker

```shell
docker run --name dd-agent \
    -p 8126:8126 \
    -e DD_API_KEY=<API_KEY> \
    -e DD_SITE="us5.datadoghq.com" \
    -v /var/run/docker.sock:/var/run/docker.sock:ro \
    -v /proc/:/host/proc/:ro \
    -v /sys/fs/cgroup/:/host/sys/fs/cgroup:ro \
    -v /var/lib/docker/containers:/var/lib/docker/containers:ro \
    gcr.io/datadoghq/agent:7
```

## Variáveis de VM para execução

`-javaagent:dd-java-agent.jar -Ddd.logs.injection=true -Ddd.trace.sample.rate=1 -Ddd.service=datadog-tracing-metrics -Ddd.env=dev -Ddd.trace.otel.enabled=true`

Dúvida: Nesse cenário, foi exibida a mensagem "Warning: dd-java-agent is being initialized more than once. Please check that you are defining -javaagent:dd-java-agent.jar only once.". O que deve ser feito para evitar essa mensagem?
Dúvida: No cenário da chave `dd.trace.otel.enabled`, a customização via OpenTelemetry também perde as capacidades adicionais específicas do DD?

## Variável de ambiente para essa aplicação

`DATADOG_API_KEY=<API_KEY>`

## Dependências para envio de métricas customizadas

```groovy
runtimeOnly 'io.micrometer:micrometer-registry-datadog'
implementation 'org.springframework.boot:spring-boot-starter-actuator'
```  

Dúvida: Nesse cenário, foi exibida a mensagem "An application key must be configured in order for unit information to be sent to Datadog.", mas não identifiquei como configurar essa key.

## Dependência para customização do trace com o OpenTelemetry

```groovy
implementation 'io.opentelemetry.instrumentation:opentelemetry-instrumentation-annotations:2.5.0'
```

## Dependência para customização do trace com o DD API

```groovy
implementation 'com.datadoghq:dd-trace-api:1.37.0'
```

## Dúvidas Gerais

- É necessário a instalação do agent no ambiente e a execução com o java-agent para envio das métricas e/ou traces?
    - Como fica esse cenário na arquitetura alvo?
    - Qual será o modelo para armazenamento do API Key?
- Como buscar pelo traceId na interface do DD?
- Como ficam e onde são exibidas as métricas quando não existe a integração do micrometer?
  - TODO: Validar sem a integração em uma nova aplicação.
  - TODO: Criar métricas customizadas com micrometer e validar localização
- Na doc informa: Make sure you only depend on the OpenTelemetry API (and not the OpenTelemetry SDK). Isso impossibilita o módulo atual de otel integrado com o Spring? No caso, para usar o DD com otel ainda seria necessário um módulo adicional somente com o OpenTelemetry API?
- TODO: Validar se na solução de log que já contém os dados do MDC, o traceId e o spanId aparecem automaticamente.
- O traceId exibido no DD corresponde ao traceId do OTEL. Isso ocorre devido porque está sendo utilizada a propriedade `dd.trace.otel.enabled`, pela dependência da API do OTEL adicionada ou por algum outro motivo?
  - TODO: Validar o traceId em um cenário sem o OTEL adicionado
