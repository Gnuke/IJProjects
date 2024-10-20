package com.example.sesac.boards;

import com.example.sesac.user.User;
import com.example.sesac.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/board")
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

    //list
    @GetMapping("/freeboard")
    public List<FreeDTO> getAllFreeBoard() {
//        List<FreeDTO> freeBoardList = service.getAll();

//        if (freeBoardList.isEmpty()) {
//            return ResponseEntity.ok().body(Collections.emptyList());  // 빈 리스트 반환
//        }
//        return ResponseEntity.ok().body(freeBoardList);
        return service.getAll();
    }
}
