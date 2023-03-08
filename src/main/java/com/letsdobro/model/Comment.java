package com.letsdobro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private ZonedDateTime createdDate;
    @Column(columnDefinition = "TEXT")
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User createdBy;
    @ManyToOne
    @JsonIgnore
    private Assignment assignment;
}
