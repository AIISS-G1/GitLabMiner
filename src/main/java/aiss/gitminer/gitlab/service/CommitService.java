package aiss.gitminer.gitlab.service;

import aiss.gitminer.gitlab.repository.CommitRepository;
import aiss.gitminer.model.Commit;
import aiss.gitminer.pagination.RestPaginationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class CommitService {

    @Autowired private CommitRepository commitRepository;

    public List<Commit> get(String id, int maxPages, int since, String token) {
        return RestPaginationHelper.unwrap(
                page -> this.commitRepository.get(id, page, LocalDate.now().minus(Period.ofDays(since)), token),
                maxPages
        );
    }
}
