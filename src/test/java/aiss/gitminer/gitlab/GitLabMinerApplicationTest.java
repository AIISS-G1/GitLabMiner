package aiss.gitminer.gitlab;

import aiss.gitminer.authentication.AuthenticationRestTemplate;
import aiss.gitminer.service.GitMinerService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class GitLabMinerApplicationTest {

    @Test
    void contextLoads() {
    }

    @Test
    void hasRestTemplateConfigured(ApplicationContext context) {
        assertThat(context.getBean(RestTemplate.class)).isNotNull();
    }

    @Test
    void hasBaseServicesConfigured(ApplicationContext context) {
        assertThat(context.getBean(AuthenticationRestTemplate.class)).isNotNull();
        assertThat(context.getBean(GitMinerService.class)).isNotNull();
    }
}
