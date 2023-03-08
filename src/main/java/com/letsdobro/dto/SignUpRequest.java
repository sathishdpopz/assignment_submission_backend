package com.letsdobro.dto;

import com.letsdobro.model.Authority;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class SignUpRequest {
    private String username;
    private LocalDate cohortStartDate;
    private String name;
    private String password;
    private List<Authority> role;
}