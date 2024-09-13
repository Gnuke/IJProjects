package com.example.demo.todo;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TodoDTO {
    private int num;
    private String title;
    private String content;
    private Date wdate;
}
