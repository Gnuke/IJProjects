package com.example.sesac.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
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

    @Column(name="joinDate", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinDate;

    @Column(name="role", nullable=false)
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String uid, String pwd, String email, Date joinDate, Role role) {
        this.uid = uid;
        this.pwd = pwd;
        this.email = email;
        this.joinDate = joinDate;
        this.role = role;
    }

}
