package com.nagarro.miniassignment.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {

    // WebClient bean for API1
    @Bean
    public WebClient webClientApi1() {
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(HttpClient.create().responseTimeout(java.time.Duration.ofMillis(2000))))
                .build();
    }

    // WebClient bean for API2
    @Bean
    public WebClient webClientApi2() {
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(HttpClient.create().responseTimeout(java.time.Duration.ofMillis(1000))))
                .build();
    }

    // WebClient bean for API3
    @Bean
    public WebClient webClientApi3() {
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(HttpClient.create().responseTimeout(java.time.Duration.ofMillis(1000))))
                .build();
    }
}
