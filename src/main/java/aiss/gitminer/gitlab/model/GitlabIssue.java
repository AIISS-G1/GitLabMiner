package aiss.gitminer.gitlab.model;

import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Issue;
import aiss.gitminer.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.List;

public class GitlabIssue extends Issue {

    @JsonProperty("iid")
    private String issueId;

    public GitlabIssue() {
        // Constructor for Jackson
    }

    public GitlabIssue(String id, String issueId, String title, String description, String state, Instant createdAt,
                       Instant updatedAt, Instant closedAt, List<String> labels, User author, User assignee,
                       Integer upvotes, Integer downvotes, String webUrl, List<Comment> comments) {
        super(id, null, title, description, state, createdAt, updatedAt, closedAt, labels, author, assignee, upvotes, downvotes, webUrl, comments);
        this.issueId = issueId;
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public Issue toIssue() {
        return new Issue(this.getId(), this.issueId, this.getTitle(), this.getDescription(), this.getState(),
                this.getCreatedAt(), this.getUpdatedAt(), this.getClosedAt(), this.getLabels(), this.getAuthor(),
                this.getAssignee(), this.getUpvotes(), this.getDownvotes(), this.getWebUrl(), this.getComments());
    }
}
