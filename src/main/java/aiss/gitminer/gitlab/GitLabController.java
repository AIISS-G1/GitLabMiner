package aiss.gitminer.gitlab;

import aiss.gitminer.exception.AuthenticationException;
import aiss.gitminer.gitlab.service.ProjectService;
import aiss.gitminer.model.Project;
import aiss.gitminer.service.GitMinerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class GitLabController {

    @Autowired private GitMinerService gitMinerService;
    @Autowired private ProjectService projectService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Project get(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authorization,
                       @PathVariable String id,
                       @RequestParam(defaultValue = "2") int sinceCommits,
                       @RequestParam(defaultValue = "20") int sinceIssues,
                       @RequestParam(defaultValue = "2") int maxPages) {
        String token = Optional.ofNullable(authorization)
                .map(s -> s.replace("Bearer ", ""))
                .orElseThrow(() -> new AuthenticationException(HttpStatus.UNAUTHORIZED));

        return this.projectService.findById(id, sinceCommits, sinceIssues, maxPages, token);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Project create(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
                          @PathVariable String id,
                          @RequestParam(defaultValue = "2") int sinceCommits,
                          @RequestParam(defaultValue = "20") int sinceIssues,
                          @RequestParam(defaultValue = "2") int maxPages) {
        Project project = this.get(authorization, id, sinceCommits, sinceIssues, maxPages);
        this.gitMinerService.uploadProject(project);
        return project;
    }
}
