package com.letsdobro.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private Long id;
    private String username;
    private List<String> roles;
}