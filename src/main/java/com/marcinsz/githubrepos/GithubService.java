package com.marcinsz.githubrepos;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
    class GithubService {
    private final GithubClient githubClient;

    GithubService(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    List<GithubRepoResponse> getRequiredData(String username) {
        return githubClient.fetchRepoList(username)
                .stream()
                .filter(repo -> !repo.fork())
                .map(repo -> {
                    List<GithubBranchResponse> branches = githubClient.fetchBranchesList(
                                    repo.owner().login(),
                                    repo.name()
                            )
                            .stream()
                            .map(branch -> new GithubBranchResponse(
                                    branch.name(),
                                    branch.commit().sha()
                            ))
                            .toList();

                    return new GithubRepoResponse(
                            repo.name(),
                            repo.owner().login(),
                            branches
                    );
                })
                .toList();
    }
}
