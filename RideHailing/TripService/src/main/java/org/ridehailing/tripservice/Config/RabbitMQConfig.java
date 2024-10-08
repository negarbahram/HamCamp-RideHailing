package org.ridehailing.tripservice.Config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String REPORTING_QUEUE = "reportingQueue";
    public static final String NOTIFICATION_QUEUE = "notificationQueue";

    @Bean
    public Queue notifQueue() {
        return new Queue(NOTIFICATION_QUEUE, false);
    }

    @Bean
    public Queue reportingQueue() {
        return new Queue(REPORTING_QUEUE, true);
    }
}