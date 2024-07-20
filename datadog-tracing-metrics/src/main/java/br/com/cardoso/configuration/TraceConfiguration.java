package br.com.cardoso.configuration;

import br.com.cardoso.listener.CorrelationIdTraceInterceptor;
import datadog.trace.api.GlobalTracer;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TraceConfiguration {

    @PostConstruct
    public void init() {
        GlobalTracer.get().addTraceInterceptor(new CorrelationIdTraceInterceptor());
    }
}
