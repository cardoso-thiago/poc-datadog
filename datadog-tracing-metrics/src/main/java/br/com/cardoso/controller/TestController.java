package br.com.cardoso.controller;

import datadog.trace.api.CorrelationIdentifier;
import datadog.trace.api.Trace;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
    private final MeterRegistry meterRegistry;

    public TestController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @GetMapping("/test")
//    @WithSpan("testeTagCustomizadaOtel")
    @Trace(operationName = "testeTagCustomizadaDD", resourceName = "TestController.test")
    public ResponseEntity<String> test() {
        LOGGER.info("Logando para visualização do traceID e SpanID.");
        System.out.println("TraceID DD " + CorrelationIdentifier.getTraceId());
        System.out.println("SpanID DD " + CorrelationIdentifier.getSpanId());

        meterRegistry.counter("custom.metric.counter").increment();
//        String traceId = Span.current().getSpanContext().getTraceId();
//        System.out.println("TraceID OTEL " + traceId);
//        System.out.println("SpanID OTEL " + Span.current().getSpanContext().getSpanId());

//        String traceIdHexString = traceId.substring(traceId.length() - 16 );
//        long datadogTraceId = Long.parseUnsignedLong(traceIdHexString, 16);
//        String datadogTraceIdString = Long.toUnsignedString(datadogTraceId);
//        System.out.println("Teste DD traceId convertido do OTEL " + datadogTraceIdString);

        return ResponseEntity.ok("OK");
    }
}
