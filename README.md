# github-repo-info-webmvc

This is a simple webapp implemented using Spring Web MVC that provides a REST service which returns details of a given Github repository. The API looks like this:

GET /repositories/{owner}/{repository-name}

```json
{
  "fullName": "",
  "description": "",
  "cloneUrl": "",
  "stars": 0,
  "createdAt": ""
}
```

## Running the app

You can run the app using:

```bash
mvn spring-boot:run
```

You can test the running app using curl:

```bash
curl localhost:8080/repositories/mateuszwenus/github-repo-info-webmvc
```

## Building and running using Docker
```bash
docker build -t github-repo-info-webmvc .
docker run -p 8080:8080 github-repo-info-webmvc
```

## TODOs

* logging
* circuit breaker
* Github API authentication?

