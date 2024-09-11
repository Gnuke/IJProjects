package com.example.demo.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TodoDAO {
    @Autowired
    private TodoDTO dto;

    public TodoDAO(TodoDTO dto) {
        this.dto = dto;
    }

    public List<TodoDTO> insert(TodoDTO dto){
        List<TodoDTO> list = new ArrayList<>();

        list.add(dto);

        for( TodoDTO t : list){
            System.out.println(t.getTitle());
            System.out.println(t.getContent());
            System.out.println(t.getWdate());
        }
        return list;
    }

    public void select(){

    }

    public void update(){

    }

    public void delete(){

    }
}
