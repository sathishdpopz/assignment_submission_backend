package com.letsdobro.service;

import com.letsdobro.enums.AssignmentStatusEnum;
import com.letsdobro.enums.AuthorityEnum;
import com.letsdobro.model.Assignment;
import com.letsdobro.model.User;
import com.letsdobro.repo.AssignmentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final AssignmentRepo assignmentRepo;

    public Assignment save(User user) {
        Assignment assignment = new Assignment();
        assignment.setStatus(AssignmentStatusEnum.PENDING_SUBMISSION.getStatus());
        assignment.setUser(user);
        assignment.setNumber(findNextAssignmentToSubmit(user));
        return assignmentRepo.save(assignment);
    }

    private Integer findNextAssignmentToSubmit(User user) {
        Set<Assignment> assignmentsByUser = assignmentRepo.findByUser(user);
        if (assignmentsByUser == null)
            return 1;
        Optional<Integer> nextAssignmentNumberOpt = assignmentsByUser.stream()
                .sorted((a1, a2) -> {
                    if (a1.getNumber() == null) return 1;
                    if (a2.getNumber() == null) return 1;
                    return a2.getNumber().compareTo(a1.getNumber());
                })
                .map(assignment -> {
                    if (assignment.getNumber() == null) return 1;
                    return assignment.getNumber() + 1;
                })
                .findFirst();
        return nextAssignmentNumberOpt.orElse(1);
    }

    public Set<Assignment> findByUser(User user) {
        boolean hasCodeReviewerRole = user.getAuthorities().stream()
                .filter(auth -> AuthorityEnum.ROLE_CODE_REVIEWER.name().equals(auth.getAuthority()))
                .count() > 0;
        if (hasCodeReviewerRole) {
            //load assignments if im in a code reviewer role
            return assignmentRepo.findByCodeReviewer(user);
        } else {
            //load assignments if im in a student role
            return assignmentRepo.findByUser(user);
        }
    }

    public Optional<Assignment> findById(Long id) {
        return assignmentRepo.findById(id);
    }


    public Assignment updateById(Assignment assignment) {
        return assignmentRepo.save(assignment);
    }

}
