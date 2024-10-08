package com.example.sesac.user;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {
    private String uid;
    private String pwd;
    private String email;
    private Date joinDate;
    private Role role;
}
