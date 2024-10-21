package com.example.sesac.boards;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreeDAO extends JpaRepository<Free, Integer> {

}
