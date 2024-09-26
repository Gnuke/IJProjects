package com.example.demo.todo;

import com.example.demo.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface TodoDAO extends JpaRepository<Todo, Integer> {
    ArrayList<Todo> findByWriter(Member writer);
}
