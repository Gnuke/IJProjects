package com.example.sesac.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {
    private String uid;
    private String pwd;
    private String email;
    private LocalDateTime joinDate;
    private Role role;

    @JsonProperty("joinDate")
    public String getFormattedJoinDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
        return joinDate.format(formatter);
    }
}
