package br.com.cardoso.listener;

import datadog.trace.api.interceptor.MutableSpan;
import datadog.trace.api.interceptor.TraceInterceptor;

import java.util.Collection;
import java.util.UUID;

public class CorrelationIdTraceInterceptor implements TraceInterceptor {

    @Override
    public Collection<? extends MutableSpan> onTraceComplete(Collection<? extends MutableSpan> collection) {
        collection.forEach(span -> span.setTag("correlationId", UUID.randomUUID().toString()));
        return collection;
    }

    @Override
    public int priority() {
        // some high unique number so this interceptor is last
        return 100;
    }
}
