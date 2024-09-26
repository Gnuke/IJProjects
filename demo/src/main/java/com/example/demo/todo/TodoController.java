package com.example.demo.todo;

import com.example.demo.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class TodoController {
    @Autowired
    private TodoService service;

    // 추가
    @PostMapping("/add")
    public Map insert( @RequestBody TodoDTO t){
        Authentication a =
                SecurityContextHolder.getContext().getAuthentication();
        System.out.println("User : " + a.getPrincipal());
        Member m = new Member( a.getName(), "", "", "");
        t.setWriter(m);
        System.out.println("나는 : " + t);
        return null;
    }

    // todo 출력
    @GetMapping("/")
    public Map list(String writer){
        Map map = new HashMap();
        //ArrayList<TodoDTO> list = service.todoList(writer);
        map.put("list", null);

        return map;
    }

    // detail
//    @GetMapping("/detail/{bnum}")
//    public Map detail(@PathVariable("bnum") int num){
//        Map map = new HashMap();
//
//        TodoDTO dto = service.detail(num);
//
//        map.put("dto", dto);
//
//        return map;
//    }

    // 수정
//    @PutMapping("/detail/{bnum}")
//    public Map update(@PathVariable("bnum") int num, @RequestBody TodoDTO dto){
//        Map map = new HashMap();
//        Date newDate = new Date();
//
//        dto.setWdate(newDate);
//        service.save(dto);
//
//        map.put("dto", dto);
//
//        return map;
//    }

    // 삭제
//    @DeleteMapping("/detail/{bnum}")
//    public void delete(@PathVariable("bnum") int num){
//        service.delete(num);
//    }
}
