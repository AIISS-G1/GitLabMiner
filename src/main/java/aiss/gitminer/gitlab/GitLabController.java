package aiss.gitminer.gitlab;

import aiss.gitminer.gitlab.service.ProjectService;
import aiss.gitminer.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class GitLabController {

    @Autowired private ProjectService projectService;

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Project create(@PathVariable String id,
                          @RequestParam(defaultValue = "2") int sinceCommits,
                          @RequestParam(defaultValue = "20") int sinceIssues,
                          @RequestParam(defaultValue = "2") int maxPages) {
        return null;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Project get(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
                       @PathVariable String id,
                       @RequestParam(defaultValue = "2") int sinceCommits,
                       @RequestParam(defaultValue = "20") int sinceIssues,
                       @RequestParam(defaultValue = "2") int maxPages) {
        String token = authorization.replace("Bearer ", "");
        return this.projectService.get(id, sinceCommits, sinceIssues, maxPages, token);
    }
}
