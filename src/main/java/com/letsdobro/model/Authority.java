package com.letsdobro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "authority")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    @JsonIgnore
    private String  authority;


    @ManyToOne(optional = false)
    private User user;



    public Authority(String authority) {
        this.authority = authority;
    }

    public Authority() {
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
