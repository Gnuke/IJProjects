package com.example.demo.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemService {
    @Autowired
    private MemDAO dao;

    @Autowired
    private PasswordEncoder encoder;

    //멤버 추가
    public void save(MemDTO dto){
        dao.save( new Member(dto.getId(), encoder.encode(dto.getPwd()), dto.getName(), dto.getEmail()));
    }

    //내정보
    public Authentication myInfo(String id){
        Member entity = dao.findById(id).orElse(null);
        if(entity != null){
            return (Authentication) new MemDTO(entity.getId(), entity.getPwd(), entity.getName(), entity.getEmail());
        }
        return null;
    }

    //탈퇴
    public void delMem(String id){ dao.deleteById(id); }
}
