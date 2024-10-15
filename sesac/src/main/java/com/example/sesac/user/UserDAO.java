package com.example.sesac.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    User findByUid(String uid);

    void deleteByUid(String uid);
}
