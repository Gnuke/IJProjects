package com.example.sesac.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="uid", unique=true, nullable=false)
    private String uid;

    @Column(name="pwd", nullable=false)
    private String pwd;

    @Column(name="email", nullable=false)
    private String email;

    @CreationTimestamp
    @Column(name="joinDate", updatable=false)
    private LocalDateTime joinDate;

    @Column(name="role", nullable=false)
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public User(String uid, String pwd, String email, LocalDateTime joinDate, Set<Role> roles) {
        this.uid = uid;
        this.pwd = pwd;
        this.email = email;
        this.joinDate = joinDate;
        this.roles = roles;
    }
}
