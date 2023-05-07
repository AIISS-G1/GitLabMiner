package aiss.gitminer.gitlab.repository;

import aiss.gitminer.authentication.AuthenticationRestTemplate;
import aiss.gitminer.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static aiss.gitminer.gitlab.GitLabMinerApplication.BASE_DIR;

@Repository
public class ProjectRepository {

    @Autowired private AuthenticationRestTemplate restTemplate;

    public Project findById(String id, String token) {
        return this.restTemplate.getForObject(BASE_DIR + "/" + id, Project.class, token);
    }
}
