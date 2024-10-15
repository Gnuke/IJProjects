package com.example.sesac.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

        map.put("flag", flag);

        return map;
    }

    // 탈퇴 시 비밀번호 확인 후 삭제
    @PostMapping("/check-password")
    public Map checkPassword(@RequestBody Map<String, String> req) {
        Map map = new HashMap();
        boolean flag = true;

        String check = req.get("check");
        String uid = req.get("uid");

        boolean isDeleted = service.pwdCheckAndDelete(uid, check);

        map.put("flag", isDeleted);

        System.out.println(map);

        return map;
    }

}
