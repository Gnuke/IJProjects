package com.example.sesac.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {
    private long id;
    private String uid;
    private String pwd;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy년 MM월 dd일")
    private LocalDateTime joinDate;
    private Set<Role> roles;

    public UserDTO(String uid, String email, LocalDateTime joinDate) {
        this.uid = uid;
        this.email = email;
        this.joinDate = joinDate;
    }
}
