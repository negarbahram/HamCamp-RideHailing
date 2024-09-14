package org.ridehailing.apigateway.Filter;


import lombok.RequiredArgsConstructor;
import org.ridehailing.apigateway.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private  RouteValidator validator;
   // @Autowired
   // private  RestTemplate template;
    @Autowired
    private JwtService jwtService;

    public AuthenticationFilter() {
        super(Config.class);
    }

    public static class Config {

    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

                if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
                    throw new RuntimeException("missing authorization header");
                }

                final String token = authHeader.substring(7);
                try {
                   // template.getForObject("http://localhost:9898//auth/validate?token=" + token, String.class);
                    jwtService.validateToken(token);

                } catch (Exception e) {
                    throw new RuntimeException("un authorized access to application");
                }
            }

            return chain.filter(exchange);
        }));
    }
}
