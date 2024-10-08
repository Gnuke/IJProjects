package com.example.sesac.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    @Autowired
    private UserDAO dao;

    //sign-up
    public void saveUser(UserDTO dto){
        dto.setJoinDate(new Date());
        dto.setRole(Role.User);

        dao.save(new User(dto.getUid(), dto.getPwd(), dto.getEmail(), dto.getJoinDate(), dto.getRole()));
    }

    //탈퇴
    public void deleteUser(String uid){
        dao.deleteByUid(uid);
    }

    //내 정보 조회
    public UserDTO getUser(String uid){
        User user = dao.findByUid(uid);
        //System.out.println("service에서 받아온 email정보 : " + user.getEmail());
        return user == null ? null : new UserDTO( user.getUid(), user.getPwd(),
                user.getEmail(), user.getJoinDate(), user.getRole() );   //비밀번호 검증 필요
    }
}
