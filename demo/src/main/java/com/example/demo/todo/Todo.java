package com.example.demo.todo;

import com.example.demo.member.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @ManyToOne
    @JoinColumn(nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member writer;

    private String title;
    private String content;
    private Date wdate;

    @PrePersist
    public void makeDate() { wdate = new Date();}
}
