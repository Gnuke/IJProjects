package com.example.demo.member;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemDTO {
    private String id;
    private String pwd;
    private String name;
    private String email;
}
