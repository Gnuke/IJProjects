package com.example.sesac.boards;

import com.example.sesac.user.User;
import com.example.sesac.user.UserDTO;
import com.example.sesac.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FreeService {
    @Autowired
    private FreeDAO dao;

    @Autowired
    private UserService userService;

    //게시글 생성
//    public void save(Map<String, String> req){
//        String uid = req.get("uid");
//        String title = req.get("title");
//        String content = req.get("content");
//
//        UserDTO user = userService.getUser(uid);
//
//        FreeDTO dto = new FreeDTO();
//        dto.setUid(user);
//        dto.setWDate(LocalDateTime.now());
//        dto.setTitle(title);
//        dto.setContent(content);
//
//        try {
//            dao.save(new Free(dto.getUid(), dto.getTitle(), dto.getContent(), dto.getWDate()));
//        }catch (Exception e){
//            System.out.println("service에서 에러 : " + e);
//        }
//    }
    //write json 형식으로 보내는데 이 때 User 타입으로 반환이 안됨 처리 어떻게 해야할 지 모르겠음

    //전체 목록 조회
    public List<FreeDTO> getAll(){
        List<Free> l = dao.findAll();
        List<FreeDTO> dtos = new ArrayList<>();

        for(Free f : l){
            dtos.add(new FreeDTO(f.getNum(), f.getUid(), f.getTitle(), f.getContent(), f.getWDate()));
        }

        return dtos;
    }
}
