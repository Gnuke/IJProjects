package com.example.sesac.boards;

import com.example.sesac.user.User;
import com.example.sesac.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class FreeController {
    @Autowired
    private FreeService service;

    @Autowired
    private UserService userService;

    //write
//    @PostMapping("/freeboard/write")
//    public Map<String, Object> write(@RequestBody FreeDTO dto ) {
//        String uid = dto.getUid(); // uid가 String 타입일 경우
//        User user = userService.getUser(uid); // uid를 이용해 User 객체를 가져오는 로직
//        dto.setUid(user);
//
//        System.out.println(dto.getUid());
//        return null;
//    } token 생성하고 그 값을 넘겨서 token 쪽에서 User 타입으로 변경해 주기
}
