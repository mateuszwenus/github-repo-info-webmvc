package com.github.mateuszwenus.github_repo_info_webmvc;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RepositoryInfoController {

	private final RepositoryInfoService repositoryInfoService;

	public RepositoryInfoController(RepositoryInfoService repositoryInfoService) {
		this.repositoryInfoService = repositoryInfoService;
	}

	@GetMapping("/repositories/{owner}/{repositoryName}")
	public ResponseEntity<RepositoryInfo> repositoryInfo(@PathVariable String owner, @PathVariable String repositoryName) {
		RepositoryInfo info = repositoryInfoService.getRepositoryInfo(owner, repositoryName);
		return ResponseEntity.ok(info);
	}

	@ExceptionHandler
	public ResponseEntity<String> handleRepositoryNotFoundException(RepositoryNotFoundException e) {
		return new ResponseEntity<String>("Repository not found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<String> handleRepositoryInfoServiceException(RepositoryInfoServiceException e) {
		return new ResponseEntity<String>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
