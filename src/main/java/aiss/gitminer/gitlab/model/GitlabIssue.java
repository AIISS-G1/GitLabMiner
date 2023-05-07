package aiss.gitminer.gitlab.model;

import aiss.gitminer.model.Issue;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GitlabIssue extends Issue {

    @JsonProperty("iid")
    private String issueId;

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
