package com.letsdobro.controller;

import com.letsdobro.dto.AssignmentResponseDto;
import com.letsdobro.enums.AuthorityEnum;
import com.letsdobro.model.Assignment;

import com.letsdobro.model.User;
import com.letsdobro.service.AssignmentService;
import com.letsdobro.service.UserService;
import com.letsdobro.util.AuthorityUtil;
import com.letsdobro.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentController {
    private final AssignmentService assignmentService;
    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<?> createAssignment(@AuthenticationPrincipal User user) {
        Assignment newAssignment = assignmentService.save(user);
        return ResponseEntity.ok(newAssignment);
    }

    @GetMapping("")
    public ResponseEntity<?> getAssignments(@AuthenticationPrincipal User user){
        Set<Assignment> assignmentsByUser = assignmentService.findByUser(user);
        return ResponseEntity.ok(assignmentsByUser);
    }

    @GetMapping("/{assignmentsId}")
    public ResponseEntity<?> findById(@PathVariable("assignmentsId") Long id, @AuthenticationPrincipal User user){
        Optional<Assignment> optAssign = assignmentService.findById(id);
        AssignmentResponseDto response = new AssignmentResponseDto(optAssign.orElse(new Assignment()));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{assignmentId}")
    public ResponseEntity<?> updateById(@PathVariable Long assignmentId,
                                        @RequestBody Assignment assignment,
                                        @AuthenticationPrincipal User user){
        //add the code reviewer to this assignment if it was claimed
        if(assignment.getCodeReviewer() != null) {
            User codeReviewer = assignment.getCodeReviewer();
            codeReviewer = userService.findUserByUsername(codeReviewer.getUsername()).orElse(new User());

            if (AuthorityUtil.hasRole(AuthorityEnum.ROLE_CODE_REVIEWER.name(), codeReviewer)) {
                assignment.setCodeReviewer(codeReviewer);
            }
        }
        Assignment updateAssignment = assignmentService.updateById(assignment);

        return ResponseEntity.ok(updateAssignment);
    }
}
