package com.example.demo.todo;

import org.springframework.beans.factory.annotation.Autowired;
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
        Map map = new HashMap();

        //System.out.println( t.getContent() + " & " + t.getTitle() );

        boolean flag = true;
        try {
            service.save(t);
        } catch (Exception e) {
            flag = false;
            e.getMessage();
        }

        map.put("flag", flag);

        return map;
    }

    // 전체 검색
    @GetMapping("")
    public Map list(){
        Map map = new HashMap();
        ArrayList<TodoDTO> list = service.selectAll();
        map.put("list", list);

        return map;
    }

    // detail
    @GetMapping("/detail/{bnum}")
    public Map detail(@PathVariable("bnum") int num){
        Map map = new HashMap();

        TodoDTO dto = service.detail(num);

        map.put("dto", dto);

        return map;
    }

    // 수정
    @PutMapping("/detail/{bnum}")
    public Map update(@PathVariable("bnum") int num, @RequestBody TodoDTO dto){
        Map map = new HashMap();
        Date newDate = new Date();

        dto.setWdate(newDate);
        service.save(dto);

        map.put("dto", dto);

        return map;
    }

    // 삭제
    @DeleteMapping("/detail/{bnum}")
    public void delete(@PathVariable("bnum") int num){
        service.delete(num);
    }
}
