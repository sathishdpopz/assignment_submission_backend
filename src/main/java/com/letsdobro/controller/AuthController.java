package com.letsdobro.controller;

import com.letsdobro.dto.JwtResponse;
import com.letsdobro.dto.SignInRequest;
import com.letsdobro.dto.SignUpRequest;
import com.letsdobro.model.Authority;
import com.letsdobro.model.User;
import com.letsdobro.repo.UserRepo;
import com.letsdobro.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    public AuthController(UserRepo userRepo,
                          PasswordEncoder passwordEncoder,

                          AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil) {
        this.userRepo = userRepo;

        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SignInRequest signInRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User userDetails = (User) authentication.getPrincipal();
        String jwt = jwtUtil.generateToken(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        JwtResponse res = new JwtResponse();
        res.setToken(jwt);
        res.setId(userDetails.getId());
        res.setUsername(userDetails.getUsername());
        res.setRoles(roles);
        return ResponseEntity.ok(res);
    }
    @PostMapping("/addStudent")
    public ResponseEntity<String> signup(@RequestBody SignUpRequest signUpRequest) {
        if (userRepo.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("username is already taken");
        }

        String hashedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(hashedPassword);
        user.setCohortStartDate(LocalDate.now());
        user.setName(signUpRequest.getName());
        user.setAuthorities(signUpRequest.getRole());
        userRepo.save(user);
        return ResponseEntity.ok("User registered success");
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestParam String token,
                                           @AuthenticationPrincipal User user){
        try {
            Boolean isValid = jwtUtil.validateToken(token, user);
            return ResponseEntity.ok(isValid);
        }catch (ExpiredJwtException ex){
            return ResponseEntity.ok(false);
        }
    }
}