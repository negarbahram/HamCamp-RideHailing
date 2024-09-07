package org.ridehailing.tripservice.Config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String REPORTING_QUEUE = "reportingQueue";

    @Bean
    public Queue reportingQueue() {
        return new Queue(REPORTING_QUEUE, true);
    }
}