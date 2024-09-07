package org.ridehailing.apigateway.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AplicationConfig {
    @Bean
    public RestTemplate template(){
        return new RestTemplate();
    }
}
