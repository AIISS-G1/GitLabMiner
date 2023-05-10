package aiss.gitminer.gitlab.repository;

import aiss.gitminer.authentication.AuthenticationRestTemplate;
import aiss.gitminer.model.Comment;
import org.springframework.stereotype.Repository;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static aiss.gitminer.gitlab.GitLabMinerApplication.BASE_DIR;

@Repository
public class CommentRepository {

    private final AuthenticationRestTemplate restTemplate;

    public CommentRepository(AuthenticationRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Comment> fetchIssueComments(String id, String issueId, int page, String token) {
        String url = UriComponentsBuilder.fromUriString(BASE_DIR + "/" + id + "/issues/" + issueId + "/notes")
                .queryParam("page", page)
                .toUriString();

        Comment[] response = Objects.requireNonNull(restTemplate.getForObject(url, Comment[].class, token));
        return Arrays.asList(response);
    }
}
