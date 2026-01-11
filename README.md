# Recruitment task -  GitHub Repositories

A Spring Boot application that integrates with the GitHub API to fetch non-fork repositories for a given user, including their branches and last commit SHAs.

## Features
- Fetches all public repositories for a specific GitHub user.
- Filters out fork repositories.
- Retrieves all branches for each repository with their last commit SHA.
- Custom Error Handling: Returns a clean JSON response with status 404 if the user is not found.

## Stack
- Java 25
- Spring Boot 4.0.1
- Maven

### Building the project
```bash
mvn clean install
```
### Running the application

```bash
mvn spring-boot:run
```

### Running tests

The project includes integration tests using WireMock to mock GitHub API responses and MockMvc to verify controller behavior.

```bash
mvn test
```

### API Usage
**Get User Repositories**

Endpoint: GET /repos/{username}

### Successful Response (200 OK):

```bash
[
  {
    "repositoryName": "EventManagement",
    "ownerLogin": "MarcinSz1993",
    "branches": [
      {
        "name": "master",
        "lastCommitSha": "e87242fee78640349207be7c49c83ad0cb5e535b"
      }
    ]
  }
]
```

### Error Response (404 Not Found):

```bash
{
  "status": 404,
  "message": "User xxxxxxxx does not exist"
}
```

### Project Structure

**GithubController:** Handles incoming HTTP requests and content negotiation.
**GithubService:** Contains business logic (filtering forks, mapping data).
**GithubClient:** Responsible for communication with GitHub API using RestClient.
**GithubExceptionHandler:** Global exception handler for consistent error responses.