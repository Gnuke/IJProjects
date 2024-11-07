package com.example.sesac.boards;

import com.example.sesac.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FreeDTO {
    private int num;
    //private User udtos;
    private String uid;
    private String title;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDateTime wDate;

    public FreeDTO(int num, User user, String title, String content, LocalDateTime wDate) {
        this.num = num;
        this.uid = user.getUid(); // User 객체의 uid만 저장
        this.title = title;
        this.content = content;
        this.wDate = wDate;
    }
}
