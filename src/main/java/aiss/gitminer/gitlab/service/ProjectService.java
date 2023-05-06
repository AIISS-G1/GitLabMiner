package aiss.gitminer.gitlab.service;

import aiss.gitminer.gitlab.repository.ProjectRepository;
import aiss.gitminer.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired private ProjectRepository projectRepository;

    @Autowired private CommitService commitService;
    @Autowired private IssueService issueService;

    public Project get(String id, int sinceCommits, int sinceIssues, int maxPages, String token) {
        Project project = this.projectRepository.get(id, token);

        project.setCommits(this.commitService.get(id, maxPages, sinceCommits, token));
        project.setIssues(this.issueService.get(id, maxPages, sinceIssues, token));

        return project;
    }
}
