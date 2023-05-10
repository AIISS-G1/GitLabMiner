package aiss.gitminer.gitlab.service;

import aiss.gitminer.gitlab.repository.CommitRepository;
import aiss.gitminer.model.Commit;
import aiss.gitminer.pagination.RestPaginationHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class CommitService {

    private final CommitRepository commitRepository;

    public CommitService(CommitRepository commitRepository) {
        this.commitRepository = commitRepository;
    }

    public List<Commit> fetchProjectCommits(String id, int maxPages, int since, String token) {
        return RestPaginationHelper.unwrap(
                page -> this.commitRepository.fetchProjectCommits(id, page, LocalDate.now().minus(Period.ofDays(since)), token),
                maxPages
        );
    }
}
