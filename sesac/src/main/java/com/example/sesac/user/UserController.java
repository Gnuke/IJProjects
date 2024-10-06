package com.example.sesac.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/join")
    public Map join(@RequestBody UserDTO dto) {
//        Map map = new HashMap();
//        boolean flag = false;
//
//        try {
//            userService.save(dto);
//            map.put("dto", dto);
//            flag = true;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        map.put("flag", flag);
//        System.out.println( "회원 정보는 : " + dto.toString() );

        //return map;
        System.out.println( "회원 정보는 : " + dto.toString() );
        return null;
    }
}
