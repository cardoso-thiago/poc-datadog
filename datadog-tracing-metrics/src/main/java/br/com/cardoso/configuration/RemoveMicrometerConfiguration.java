package br.com.cardoso.configuration;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RemoveMicrometerConfiguration {

    @Bean
    public MeterRegistryCustomizer<MeterRegistry> metricsRegistryConfig() {
        return registry -> registry.config().meterFilter(MeterFilter.deny(id -> !id.getName().startsWith("custom.metric")));
    }
}
