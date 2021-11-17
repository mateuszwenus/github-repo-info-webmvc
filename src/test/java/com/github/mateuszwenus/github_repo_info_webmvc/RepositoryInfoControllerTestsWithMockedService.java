package com.github.mateuszwenus.github_repo_info_webmvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class RepositoryInfoControllerTestsWithMockedService {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RepositoryInfoService repositoryInfoService;

	@Test
	public void shouldReturnHttp200WhenServiceReturnsValidResponse() throws Exception {
		RepositoryInfo repoInfo = new RepositoryInfo("Mock repository", "description", "http://clone", 0, "now");
		when(repositoryInfoService.getRepositoryInfo(any(), any())).thenReturn(repoInfo);

		mockMvc.perform(get("/repositories/owner/repo"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.fullName", is("Mock repository")))
			.andExpect(jsonPath("$.description", is("description")))
			.andExpect(jsonPath("$.cloneUrl", is("http://clone")))
			.andExpect(jsonPath("$.stars", is(0)))
			.andExpect(jsonPath("$.createdAt", is("now")));
	}
	
	@Test
	public void shouldReturnHttp404WhenServiceReturnsNotFoundException() throws Exception {
		when(repositoryInfoService.getRepositoryInfo(any(), any())).thenThrow(new RepositoryNotFoundException());

		mockMvc.perform(get("/repositories/owner/repo"))
			.andDo(print())
			.andExpect(status().isNotFound());
	}

	@Test
	public void shouldReturnHttp5xxWhenServiceReturnsGenericException() throws Exception {
		when(repositoryInfoService.getRepositoryInfo(any(), any())).thenThrow(new RepositoryInfoServiceException());
		
		mockMvc.perform(get("/repositories/owner/repo"))
			.andDo(print())
			.andExpect(status().isInternalServerError());
	}
}
