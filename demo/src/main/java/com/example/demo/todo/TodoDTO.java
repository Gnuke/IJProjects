package com.example.demo.todo;

import com.example.demo.member.Member;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TodoDTO {
    private int num;
    private Member writer;
    private String title;
    private String content;
    private Date wdate;
}
