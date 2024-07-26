package br.com.cardoso.controller;

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
    @Trace(operationName = "testeTagCustomizadaDD", resourceName = "TestController.test")
    public ResponseEntity<String> test() {
        LOGGER.info("Logando para visualização do traceID e SpanID.");
        meterRegistry.counter("custom.metric.counter").increment();
        return ResponseEntity.ok("OK");
    }
}
