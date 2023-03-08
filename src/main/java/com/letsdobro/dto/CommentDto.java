package com.letsdobro.dto;

import com.letsdobro.model.Assignment;
import com.letsdobro.model.User;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentDto {
    private Long id;
    private Long assignmentId;
    private String user;
    private String text;
    private ZonedDateTime createdDate;
}
