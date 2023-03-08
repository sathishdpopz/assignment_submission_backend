package com.letsdobro.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@ToString
@Entity
public class Assignment {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private Integer number;
    private String status;
    private String githubUrl;
    private String branch;
    private String codeReviewVideoUrl;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne
    private User codeReviewer;



    public Assignment() {
    }

    public Assignment(Long id, Integer number, String  status, String githubUrl, String branch, String codeReviewVideoUrl, User user) {
        this.id = id;
        this.number = number;
        this.status = status;
        this.githubUrl = githubUrl;
        this.branch = branch;
        this.codeReviewVideoUrl = codeReviewVideoUrl;
        this.user = user;
    }
}


