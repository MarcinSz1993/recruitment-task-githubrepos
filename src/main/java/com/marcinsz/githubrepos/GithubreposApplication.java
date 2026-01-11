package com.marcinsz.githubrepos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(GithubProperties.class)
public class GithubreposApplication {

    static void main(String[] args) {
        SpringApplication.run(GithubreposApplication.class, args);
    }

}
