package aiss.gitminer.gitlab.model;

import aiss.gitminer.model.Issue;
import aiss.gitminer.model.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GitlabIssueTest {

    @Test
    void givenGitLabIssue_whenMappingToIssue_fieldsMatch() {
        GitlabIssue gitlabIssue = new GitlabIssue("1556497126", "5429", "New extension points",
                """
                        Adds new extension points to allow post analysis modification of coverage and mutation analysis results.\r
                        \r
                        Extensions points have multiple potential uses, but first use case is supporting the 'un-inlining' of inlined kotlin functions.\r
                        \r
                        Change requires alteration of existing interfaces which may be incompatible with some third party plugins.""",
                "closed",
                "2023-01-25T11:35:30Z", "2023-01-25T13:06:20Z", "2023-01-25T13:06:19Z",
                List.of("label"),
                new User("1891135", "hcoles", "Henry Coles",
                        "https://avatars.githubusercontent.com/u/1891135?v=4",
                        "https://api.github.com/users/hcoles"
                ),
                null,
                0, 0, "https://api.github.com/repos/hcoles/pitest/issues/1150",
                null
        );
        Issue issue = gitlabIssue.toIssue();

        assertThat(issue.getId()).isEqualTo(gitlabIssue.getId());
        assertThat(issue.getRefId()).isEqualTo(gitlabIssue.getIssueId());
        assertThat(issue.getTitle()).isEqualTo(gitlabIssue.getTitle());
        assertThat(issue.getDescription()).isEqualTo(gitlabIssue.getDescription());
        assertThat(issue.getState()).isEqualTo(gitlabIssue.getState());
        assertThat(issue.getCreatedAt()).isEqualTo(gitlabIssue.getCreatedAt());
        assertThat(issue.getUpdatedAt()).isEqualTo(gitlabIssue.getUpdatedAt());
        assertThat(issue.getClosedAt()).isEqualTo(gitlabIssue.getClosedAt());
        assertThat(issue.getLabels()).isEqualTo(gitlabIssue.getLabels());
        assertThat(issue.getAuthor()).isEqualTo(gitlabIssue.getAuthor());
        assertThat(issue.getAssignee()).isNull();
        assertThat(issue.getUpvotes()).isEqualTo(gitlabIssue.getUpvotes());
        assertThat(issue.getDownvotes()).isEqualTo(gitlabIssue.getDownvotes());
        assertThat(issue.getWebUrl()).isEqualTo(gitlabIssue.getWebUrl());
        assertThat(issue.getComments()).isNull();
    }
}
