package aiss.gitminer.gitlab.repository;

import aiss.gitminer.authentication.AuthenticationRestTemplate;
import aiss.gitminer.model.Project;
import org.springframework.stereotype.Repository;

import static aiss.gitminer.gitlab.GitLabMinerApplication.BASE_DIR;

@Repository
public class ProjectRepository {

    private final AuthenticationRestTemplate restTemplate;

    public ProjectRepository(AuthenticationRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Project findById(String id, String token) {
        return this.restTemplate.getForObject(BASE_DIR + "/" + id, Project.class, token);
    }
}
