package com.example.demo.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TodoController {
    @Autowired
    private TodoDAO dao;

    //추가
    @PostMapping("/add")
    public Map add(TodoDTO t){
        Map map = new HashMap();
        boolean flag = false;

        try{
            dao.insert(t);
            flag = true;
        }catch(Exception e){
            System.out.println(e);
        }

        map.put("flag",flag);
        return map;
    }
}
