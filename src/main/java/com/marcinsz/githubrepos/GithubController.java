package com.marcinsz.githubrepos;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/repos")
    class GithubController {

    private final GithubService githubService;

    GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping(value = "/{username}",produces = "application/json")
    ResponseEntity<List<GithubRepoResponse>> getRequiredData(@PathVariable String username){
        return ResponseEntity.ok(githubService.getRequiredData(username));
    }
}
