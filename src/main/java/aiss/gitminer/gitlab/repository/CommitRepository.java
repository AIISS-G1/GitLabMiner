package aiss.gitminer.gitlab.repository;

import aiss.gitminer.authentication.AuthenticationRestTemplate;
import aiss.gitminer.model.Commit;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CommitRepository {

    @Autowired private AuthenticationRestTemplate restTemplate;

    public List<Commit> get(String id, int page, LocalDate since, String token) {
        String url = UriComponentsBuilder.fromUriString(BASE_DIR + "/" + id + "/repository/commits")
                .queryParam("page", page)
                .queryParamIfPresent("since", Optional.ofNullable(since)
                        .map(date -> date.format(DateTimeFormatter.ISO_DATE)))
                .toUriString();

        Commit[] response = Objects.requireNonNull(this.restTemplate.getForObject(url, Commit[].class, token));
        return Arrays.asList(response);
    }
}
