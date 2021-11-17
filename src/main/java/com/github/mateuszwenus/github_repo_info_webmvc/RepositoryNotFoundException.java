package com.github.mateuszwenus.github_repo_info_webmvc;

public class RepositoryNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6364593545765696036L;

	public RepositoryNotFoundException() {
	}

	public RepositoryNotFoundException(Throwable cause) {
		super(cause);
	}
}
