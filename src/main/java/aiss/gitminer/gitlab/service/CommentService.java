package aiss.gitminer.gitlab.service;

import aiss.gitminer.gitlab.repository.CommentRepository;
import aiss.gitminer.model.Comment;
import aiss.gitminer.pagination.RestPaginationHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> fetchIssueComments(String id, String issueId, int maxPages, String token) {
        return RestPaginationHelper.unwrap(
                page -> this.commentRepository.fetchIssueComments(id, issueId, page, token),
                maxPages
        );
    }
}
