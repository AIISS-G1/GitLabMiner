package aiss.gitminer.gitlab;

import aiss.gitminer.exception.AuthenticationException;
import aiss.gitminer.gitlab.service.ProjectService;
import aiss.gitminer.model.Project;
import aiss.gitminer.service.GitMinerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GitLabControllerTest {

    @InjectMocks private GitLabController gitHubController;

    @Mock private ProjectService projectService;
    @Mock private GitMinerService gitMinerService;

    @Test
    void givenNoAuthorizationHeader_throwsAuthenticationExceptionUnauthorized() {
        assertThatThrownBy(() -> gitHubController.get(null, "foo", 2, 5, 2))
                .isInstanceOfSatisfying(
                        AuthenticationException.class,
                        exception -> assertThat(exception.getStatus()).isEqualTo(HttpStatus.UNAUTHORIZED)
                );
    }

    @Test
    void givenAuthenticationHeaderWithToken_whenCallingGet_projectServiceIsCalledWithThatToken() {
        ArgumentCaptor<String> tokenCapture = ArgumentCaptor.forClass(String.class);
        when(projectService.findById(anyString(), anyInt(), anyInt(), anyInt(), tokenCapture.capture())).thenReturn(new Project());

        String token = UUID.randomUUID().toString();
        gitHubController.get("Bearer " + token, "foo", 2, 5, 2);

        assertThat(tokenCapture.getValue()).isEqualTo(token);
    }

    @Test
    void givenCorrectParameters_whenCallingCreate_gitMinerServiceUploadIsCalled() {
        when(projectService.findById(anyString(), anyInt(), anyInt(), anyInt(), anyString())).thenReturn(new Project());
        doNothing().when(gitMinerService).uploadProject(any(Project.class));

        String token = UUID.randomUUID().toString();
        gitHubController.create("Bearer " + token, "foo", 2, 5, 2);

        verify(gitMinerService).uploadProject(any(Project.class));
    }
}
