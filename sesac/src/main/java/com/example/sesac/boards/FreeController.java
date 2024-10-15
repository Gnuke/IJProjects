package com.example.sesac.boards;

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

    //write
    @PostMapping("/freeboard/write")
    public Map write(@RequestBody Map<String, String> req ) {
        String uid = req.get("uid");
        String title = req.get("title");
        String content = req.get("content");

    //write json 형식으로 보내는데 이 때 User 타입으로 반환이 안됨 처리 어떻게 해야할 지 모르겠음
        return null;
    }
}
