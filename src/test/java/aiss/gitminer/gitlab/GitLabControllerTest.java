package aiss.gitminer.gitlab;

import aiss.gitminer.gitlab.service.ProjectService;
import aiss.gitminer.model.Project;
import aiss.gitminer.service.GitMinerService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GitLabController.class)
@Import(GitLabController.class)
class GitLabControllerTest {

    @Autowired MockMvc mockMvc;

    @MockBean private ProjectService projectService;
    @MockBean private GitMinerService gitMinerService;

    @Configuration
    public static class RestTemplateConfig {
        @Bean
        public RestTemplateBuilder restTemplateBuilder() {
            return new RestTemplateBuilder();
        }
    }

    @Test
    void givenNoAuthorizationHeader_respondsWithUnauthorized() throws Exception {
        this.mockMvc.perform(get("/{id}", "foo", 2, 5, 2))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void givenAuthenticationHeaderWithToken_whenCallingGet_projectServiceIsCalledWithThatToken() throws Exception {
        ArgumentCaptor<String> tokenCapture = ArgumentCaptor.forClass(String.class);
        when(this.projectService.findById(anyString(), anyInt(), anyInt(), anyInt(), tokenCapture.capture())).thenReturn(new Project());

        String token = UUID.randomUUID().toString();
        MockHttpServletRequestBuilder request = get("/{id}", "foo", 2, 5, 2)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token);

        this.mockMvc.perform(request);
        assertThat(tokenCapture.getValue()).isEqualTo(token);
    }

    @Test
    void givenAProject_whenCallingPost_gitMinerServiceUploadIsCalledWithThatProject() throws Exception {
        Project dummyProject = new Project();

        when(this.projectService.findById(anyString(), anyInt(), anyInt(), anyInt(), anyString())).thenReturn(dummyProject);
        doNothing().when(gitMinerService).uploadProject(dummyProject);

        String token = UUID.randomUUID().toString();
        MockHttpServletRequestBuilder request = post("/{id}", "foo", 2, 5, 2)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token);

        this.mockMvc.perform(request);
        verify(gitMinerService).uploadProject(dummyProject);
    }
}
