package com.letsdobro.service;

import com.letsdobro.dto.CommentDto;
import com.letsdobro.model.Assignment;
import com.letsdobro.model.Comment;
import com.letsdobro.model.User;
import com.letsdobro.repo.AssignmentRepo;
import com.letsdobro.repo.CommentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepo commentRepo;
    private final AssignmentRepo assignmentRepo;

    public Comment save(CommentDto commentDto, User user) {

        Comment comment = new Comment();
        Assignment assignment = assignmentRepo.getReferenceById(commentDto.getAssignmentId());

        comment.setId(commentDto.getId());
        comment.setAssignment(assignment);
        comment.setCreatedBy(user);
        comment.setText(commentDto.getText());
        if (comment.getId() == null) {
            comment.setCreatedDate(ZonedDateTime.now());
        } else {
            comment.setCreatedDate(commentDto.getCreatedDate());
        }
        return commentRepo.save(comment);
    }

    public Set<Comment> getCommentsByAssignmentId(Long assignmentId) {
        Set<Comment> comments = commentRepo.findByAssignmentId(assignmentId);
        return comments;
    }

    public boolean delete(Long commentId) {
        Comment comment = commentRepo.getReferenceById(commentId);
        commentRepo.delete(comment);
        return true;
    }
}
