package com.example.demo.todo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class TodoDTO {
    private String title;
    private String content;
    private Date wdate;

    public TodoDTO(String title, String content, Date wdate) {
        this.title = title;
        this.content = content;
        this.wdate = wdate;
    }
}
