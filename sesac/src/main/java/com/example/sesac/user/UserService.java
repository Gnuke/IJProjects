package com.example.sesac.user;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

@Service
public class UserService {
    @Autowired
    private UserDAO dao;

    //sign-up
    @Transactional
    public void saveUser(UserDTO dto){
        dto.setJoinDate(LocalDateTime.now());
        dto.setRole(Role.User);

        dao.save(new User(dto.getUid(), dto.getPwd(), dto.getEmail(), dto.getJoinDate(), dto.getRole()));
    }

    //내 정보 조회
    public UserDTO getUser(String uid){
        User user = dao.findByUid(uid);

        return user == null ? null : new UserDTO( user.getUid(), user.getPwd(),
                user.getEmail(), user.getJoinDate(), user.getRole() );   //비밀번호 검증 필요
    }

    // 비밀번호 확인 후 삭제
    @Transactional
    public boolean pwdCheckAndDelete(String uid, String check){
        User user = dao.findByUid(uid);

        // 비밀번호 일치 여부 확인
        if (user.getPwd().equals(check)) {
            System.out.println("비밀번호가 일치합니다. 사용자 삭제.");
            dao.deleteByUid(uid); // 비밀번호가 맞으면 사용자 삭제
            return true;
        }

        return false;
    }
}
