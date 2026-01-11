package com.marcinsz.githubrepos;

import java.util.List;

record GithubRepoResponse(String repositoryName, String ownerLogin, List<GithubBranchResponse> branches) {
}
