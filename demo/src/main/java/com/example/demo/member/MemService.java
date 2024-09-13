package com.example.demo.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemService {
    @Autowired
    private MemDAO dao;

    //멤버 추가
    public void save(MemDTO dto){
        dao.save( new Member(dto.getId(), dto.getPwd(), dto.getName(), dto.getEmail()));
    }

    //내정보
    public MemDTO myInfo(String id){
        Member entity = dao.findById(id).orElse(null);
        if(entity != null){
            return new MemDTO(entity.getId(), entity.getPwd(), entity.getName(), entity.getEmail());
        }
        return null;
    }

    //탈퇴
    public void delMem(String id){ dao.deleteById(id); }
}