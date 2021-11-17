package com.github.mateuszwenus.github_repo_info_webmvc;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.client.RestTemplate;

@Service
public class GithubRepositoryInfoService implements RepositoryInfoService {

	private final RestTemplate restTemplate;

	public GithubRepositoryInfoService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public RepositoryInfo getRepositoryInfo(String owner, String repositoryName) {
		try {
			GithubResponse githubResponse = restTemplate.getForObject("/repos/" + owner + "/" + repositoryName, GithubResponse.class);
			return new RepositoryInfo(githubResponse);
		} catch (NotFound e) {
			throw new RepositoryNotFoundException(e);
		} catch (Exception e) {
			throw new RepositoryInfoServiceException(e);
		}
	}

}
