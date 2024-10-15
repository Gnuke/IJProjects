package com.example.sesac.boards;

import com.example.sesac.user.User;
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
    private int id;
    private User uid;
    private String title;
    private String content;
    private LocalDateTime wDate;

    @JsonProperty("wDate")
    public String getFormattedWDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return wDate.format(formatter);
    }
}
