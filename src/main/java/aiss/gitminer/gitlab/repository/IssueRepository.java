package aiss.gitminer.gitlab.repository;

import aiss.gitminer.authentication.AuthenticationRestTemplate;
import aiss.gitminer.gitlab.model.GitlabIssue;
import org.springframework.stereotype.Repository;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static aiss.gitminer.gitlab.GitLabMinerApplication.BASE_DIR;

@Repository
public class IssueRepository {

    private final AuthenticationRestTemplate restTemplate;

    public IssueRepository(AuthenticationRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<GitlabIssue> fetchProjectIssues(String id, int page, LocalDate since, String token) {
        String url = UriComponentsBuilder.fromUriString(BASE_DIR + "/" + id + "/issues")
                .queryParam("page", page)
                .queryParamIfPresent("created_after", Optional.ofNullable(since)
                        .map(date -> date.format(DateTimeFormatter.ISO_DATE)))
                .toUriString();

        GitlabIssue[] response = Objects.requireNonNull(this.restTemplate.getForObject(url, GitlabIssue[].class, token));
        return Arrays.asList(response);
    }
}
