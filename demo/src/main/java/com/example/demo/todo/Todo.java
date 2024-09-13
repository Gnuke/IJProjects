package com.example.demo.todo;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num;

    private String title;
    private String content;
    private Date wdate;

    @PrePersist
    public void makeDate() { wdate = new Date();}
}
