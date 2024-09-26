package com.example.demo.todo;

import com.example.demo.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoDAO dao;

    // 추가 , 수정
    public TodoDTO save(TodoDTO t){
        Todo entity = dao.save(new Todo(t.getNum(), t.getWriter(),t.getTitle(), t.getContent(), t.getWdate()));

        return new TodoDTO(entity.getNum(), entity.getWriter(),entity.getTitle(), entity.getContent(), entity.getWdate());
    }

    //todo 리스트
    public ArrayList<TodoDTO> todoList(String writer){
        List<Todo> l = dao.findByWriter( new Member(writer, "", "", ""));
        ArrayList<TodoDTO> list = new ArrayList<>();
        for(Todo t : l){
            list.add(new TodoDTO(t.getNum(), t.getWriter(),t.getTitle(), t.getContent(), t.getWdate()));
        }
        return list;
    }

    // detail
    public TodoDTO detail(int num){
        Todo entity = dao.findById(num).orElse(null);
        if(entity != null){
            return new TodoDTO( entity.getNum(), entity.getWriter(), entity.getTitle(), entity.getContent(), entity.getWdate());
        }
        return null;
    }

    // 삭제
    public void delete(int num){ dao.deleteById(num);}
}
