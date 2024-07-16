package br.com.cardoso.controller;

import datadog.trace.api.CorrelationIdentifier;
import datadog.trace.api.Trace;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    @WithSpan("testeTagCustomizadaOtel")
    @Trace(operationName = "testeTagCustomizadaDD", resourceName = "TestController.test     ")
    public ResponseEntity<String> test() {
        System.out.println("TraceID DD " + CorrelationIdentifier.getTraceId());
        System.out.println("SpanID DD " + CorrelationIdentifier.getSpanId());
        String traceId = Span.current().getSpanContext().getTraceId();
        System.out.println("TraceID OTEL " + traceId);
        System.out.println("SpanID OTEL " + Span.current().getSpanContext().getSpanId());

        String traceIdHexString = traceId.substring(traceId.length() - 16 );
        long datadogTraceId = Long.parseUnsignedLong(traceIdHexString, 16);
        String datadogTraceIdString = Long.toUnsignedString(datadogTraceId);
        System.out.println("Teste DD traceId convertido do OTEL " + datadogTraceIdString);

        return ResponseEntity.ok("OK");
    }
}
