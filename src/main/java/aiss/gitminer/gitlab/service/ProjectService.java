package aiss.gitminer.gitlab.service;

import aiss.gitminer.gitlab.repository.ProjectRepository;
import aiss.gitminer.model.Project;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    private final CommitService commitService;
    private final IssueService issueService;

    public ProjectService(ProjectRepository projectRepository, CommitService commitService, IssueService issueService) {
        this.projectRepository = projectRepository;
        this.commitService = commitService;
        this.issueService = issueService;
    }

    public Project findById(String id, int sinceCommits, int sinceIssues, int maxPages, String token) {
        Project project = this.projectRepository.findById(id, token);

        project.setCommits(this.commitService.fetchProjectCommits(id, maxPages, sinceCommits, token));
        project.setIssues(this.issueService.fetchProjectIssues(id, maxPages, sinceIssues, token));

        return project;
    }
}
