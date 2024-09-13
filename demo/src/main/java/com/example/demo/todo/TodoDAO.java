package com.example.demo.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoDAO extends JpaRepository<Todo, Integer> {

}