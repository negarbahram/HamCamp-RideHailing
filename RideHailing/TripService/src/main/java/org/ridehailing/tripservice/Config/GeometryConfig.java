package org.ridehailing.tripservice.Config;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeometryConfig {
    @Bean
    public JtsModule jtsModule() {
        return new JtsModule();
    }
}
