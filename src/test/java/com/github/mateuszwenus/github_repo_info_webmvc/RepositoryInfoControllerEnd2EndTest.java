package com.github.mateuszwenus.github_repo_info_webmvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RepositoryInfoControllerEnd2EndTest {

	private static final String OWNER = "mateuszwenus";
	private static final String REPOSITORY_NAME = "github-repo-info-webmvc";
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Test
	public void shouldReturnHttp200ForExistingRepository() {
		ResponseEntity<RepositoryInfo> response = testRestTemplate.getForEntity("/repositories/" + OWNER + "/" + REPOSITORY_NAME, RepositoryInfo.class);
		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		assertThat(response.hasBody(), is(true));
	}

	@Test
	public void shouldReturnHttp404WhenOwnerDoesNotExist() {
		ResponseEntity<String> response = testRestTemplate.getForEntity("/repositories/" + OWNER + OWNER + "/" + REPOSITORY_NAME, String.class);
		assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
	}

	@Test
	public void shouldReturnHttp404WhenRepositoryDoesNotExist() {
		ResponseEntity<String> response = testRestTemplate.getForEntity("/repositories/" + OWNER + "/" + REPOSITORY_NAME + REPOSITORY_NAME, String.class);
		assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
	}
}
