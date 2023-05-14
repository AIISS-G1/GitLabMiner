package aiss.gitminer.gitlab.model;

import aiss.gitminer.model.Issue;
import aiss.gitminer.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GitlabIssueTest {

    private GitlabIssue dummyIssue;

    @BeforeEach
    void setUp() {
        this.dummyIssue = new GitlabIssue("1556497126", "5429", "New extension points",
                """
                        Adds new extension points to allow post analysis modification of coverage and mutation analysis results.\r
                        \r
                        Extensions points have multiple potential uses, but first use case is supporting the 'un-inlining' of inlined kotlin functions.\r
                        \r
                        Change requires alteration of existing interfaces which may be incompatible with some third party plugins.""",
                "closed",
                Instant.parse("2023-01-25T11:35:30Z"), Instant.parse("2023-01-25T13:06:20Z"), Instant.parse("2023-01-25T13:06:19Z"),
                List.of("label"),
                new User("1891135", "hcoles", "Henry Coles",
                        "https://avatars.githubusercontent.com/u/1891135?v=4",
                        "https://api.github.com/users/hcoles"
                ),
                null,
                0, 0, "https://api.github.com/repos/hcoles/pitest/issues/1150",
                null
        );
    }

    @Test
    void givenGitLabIssue_whenMappingToIssue_fieldsMatch() {
        Issue expected = new Issue("1556497126", "5429", "New extension points",
                """
                        Adds new extension points to allow post analysis modification of coverage and mutation analysis results.\r
                        \r
                        Extensions points have multiple potential uses, but first use case is supporting the 'un-inlining' of inlined kotlin functions.\r
                        \r
                        Change requires alteration of existing interfaces which may be incompatible with some third party plugins.""",
                "closed",
                Instant.parse("2023-01-25T11:35:30Z"), Instant.parse("2023-01-25T13:06:20Z"), Instant.parse("2023-01-25T13:06:19Z"),
                List.of("label"),
                new User("1891135", "hcoles", "Henry Coles",
                        "https://avatars.githubusercontent.com/u/1891135?v=4",
                        "https://api.github.com/users/hcoles"
                ),
                null,
                0, 0, "https://api.github.com/repos/hcoles/pitest/issues/1150",
                null
        );
        Issue actual = this.dummyIssue.toIssue();

        assertThat(actual).isEqualTo(expected);
    }
}
