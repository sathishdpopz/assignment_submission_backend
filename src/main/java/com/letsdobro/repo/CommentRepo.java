package com.letsdobro.repo;

import com.letsdobro.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c where c.assignment.id = :assignmentId")
    Set<Comment> findByAssignmentId(Long assignmentId);
}
