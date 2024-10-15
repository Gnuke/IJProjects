package com.example.sesac.boards;

import com.example.sesac.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "freeboard")
public class Free {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="num")
    private int num;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="uid")
    private User uid;

    @Column(name="title", nullable=false)
    private String title;

    @Column(name="content", nullable=false)
    private String content;

    @Column(name="wDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime wDate = LocalDateTime.now();

    public Free(User uid, String title, String content, LocalDateTime wDate) {
        this.uid = uid;
        this.title = title;
        this.content = content;
        this.wDate = wDate;
    }
}
