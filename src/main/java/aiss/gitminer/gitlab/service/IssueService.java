package aiss.gitminer.gitlab.service;

import aiss.gitminer.gitlab.model.GitlabIssue;
import aiss.gitminer.gitlab.repository.IssueRepository;
import aiss.gitminer.model.Issue;
import aiss.gitminer.pagination.RestPaginationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class IssueService {

    @Autowired private IssueRepository issueRepository;
    @Autowired private CommentService commentService;

    public List<Issue> fetchProjectIssues(String id, int maxPages, int since, String token) {
        List<GitlabIssue> issues = RestPaginationHelper.unwrap(
                page -> this.issueRepository.fetchProjectIssues(id, page, LocalDate.now().minus(Period.ofDays(since)), token),
                maxPages
        );

        return issues.stream()
                .peek(issue -> issue.setComments(this.commentService.fetchIssueComments(id, issue.getIssueId(), 1, token)))
                .map(GitlabIssue::toIssue)
                .toList();
    }
}
