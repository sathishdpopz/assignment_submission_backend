package com.letsdobro.repo;

import com.letsdobro.model.Assignment;
import com.letsdobro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AssignmentRepo extends JpaRepository<Assignment, Long> {
    Set<Assignment> findByUser(User user);

    @Query("select a from Assignment a where (a.status = 'submitted' and (a.codeReviewer is null or a.codeReviewer = :codeReviewer)) or a.codeReviewer = :codeReviewer")
    Set<Assignment> findByCodeReviewer(User codeReviewer);
}
