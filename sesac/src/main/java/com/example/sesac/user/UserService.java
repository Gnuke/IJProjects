package com.example.sesac.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDAO dao;

    // user 추가
    public void save(UserDTO dto) {
        dao.save(new User(dto.getUid(), dto.getPassword(), dto.getEmail(), Role.USER));
    }
}
