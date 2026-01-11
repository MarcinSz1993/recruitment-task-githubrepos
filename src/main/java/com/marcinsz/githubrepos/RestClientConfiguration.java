package com.marcinsz.githubrepos;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
    class RestClientConfiguration {

        @Bean
        RestClient restClient(GithubProperties githubProperties){
        return RestClient.builder()
                .baseUrl(githubProperties.baseUrl())
                .defaultHeader("User-Agent", "Recruitment task")
                .defaultHeader("Accept","application/json")
                .build();
    }
}
