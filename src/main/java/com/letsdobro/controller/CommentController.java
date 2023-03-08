package com.letsdobro.controller;

import com.letsdobro.dto.CommentDto;
import com.letsdobro.model.Assignment;
import com.letsdobro.model.Comment;
import com.letsdobro.model.User;
import com.letsdobro.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody CommentDto commentDto, @AuthenticationPrincipal User user) {

        Comment comment = commentService.save(commentDto, user);
        return ResponseEntity.ok(comment);
    }

    @GetMapping
    public ResponseEntity<Set<Comment>> getCommentsByAssignment(@RequestParam Long assignmentId){
        Set<Comment> comments =commentService.getCommentsByAssignmentId(assignmentId);
        return ResponseEntity.ok(comments);
    }

    @PutMapping("{commentId}")
    public ResponseEntity<Comment> updateComment(@RequestBody CommentDto commentDto, @AuthenticationPrincipal User user){
        Comment comment = commentService.save(commentDto, user);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("{commentId}")
    public ResponseEntity<Map<String, Boolean>> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal User user){
        try {
        boolean deleted = commentService.delete(commentId);
        Map<String , Boolean> response =new HashMap<>();
        response.put("deleted",deleted);
        return ResponseEntity.ok(response);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
