package com.example.sesac.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDTO {
    private String uid;
    private String password;
    private String email;
    private Role role;
}
