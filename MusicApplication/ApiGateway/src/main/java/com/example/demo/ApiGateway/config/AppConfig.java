package com.example.demo.ApiGateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes().
                route(p->p
                        .path("/userauth/**")
                        .uri("http://user-authentication-service:8082/")
                )
                .route(p->p
                        .path("/usertrack/app/**")
                        .uri("http://user-track-service:64100/"))
                .build();
    }
}
