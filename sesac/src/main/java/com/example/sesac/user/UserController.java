package com.example.sesac.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService service;

    //signup
    @PostMapping("/signup")
    public Map signup(@RequestBody UserDTO dto) {
        Map map = new HashMap();

        boolean flag = true;

        try {
            service.saveUser(dto);
            map.put("memberInfo", dto);
        }catch (Exception e) {
            flag = false;
            throw new RuntimeException(e);
        }

        map.put("flag", flag);
        return map;
    }

    @PostMapping("/signin")
    public Map signin(@RequestBody UserDTO dto) {
        Map map = new HashMap();
        boolean flag = true;

        UserDTO memberInfo = service.getUser(dto.getUid());
        if(memberInfo != null) {
            map.put("memberInfo", memberInfo);
        }else{
            flag = false;
        }

        System.out.println("memberInfo E : " + memberInfo.getEmail());

        map.put("flag", flag);

        return map;
    }
}
