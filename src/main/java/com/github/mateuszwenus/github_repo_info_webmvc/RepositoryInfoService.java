package com.github.mateuszwenus.github_repo_info_webmvc;

public interface RepositoryInfoService {

	RepositoryInfo getRepositoryInfo(String owner, String repositoryName);

}
