package com.marcinsz.githubrepos;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
class GithubClient {
    private final RestClient restClient;

    GithubClient(RestClient restClient) {
        this.restClient = restClient;
    }

    List<GithubRepo> fetchRepoList(String username) {
        return restClient.get()
                .uri("/users/{username}/repos", username)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (_, _) -> {
                    throw new UserNotFoundException("User " + username + " does not exist");
                })
                .body(new ParameterizedTypeReference<>() {
                });

    }

    List<Branch> fetchBranchesList(String ownerName, String repoName) {
        return restClient.get()
                .uri("/repos/{owner}/{repo}/branches", ownerName, repoName)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }


}
