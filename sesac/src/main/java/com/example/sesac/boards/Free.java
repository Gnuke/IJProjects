package com.example.sesac.boards;

import com.example.sesac.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name="uid", nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(name="title", nullable=false)
    private String title;

    @Column(name="content", nullable=false)
    private String content;

    @Column(name="wDate", updatable=false)
    private LocalDateTime wDate = LocalDateTime.now();

    public Free(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
    }
}
