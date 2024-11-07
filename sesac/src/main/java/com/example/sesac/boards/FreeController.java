package com.example.sesac.boards;

import com.example.sesac.auth.SecurityUtil;
import com.example.sesac.pagination.PaginationService;
import com.example.sesac.user.User;
import com.example.sesac.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/freeboard")
public class FreeController {
    @Autowired
    private FreeService service;

    @Autowired
    private UserService userService;

    //write
    @PostMapping("/write")
    public Map write(@RequestBody FreeDTO dto ) {

        Map map = new HashMap();
        String uid = SecurityUtil.getCurrentUserId();
        boolean flag = false;

        dto.setUid(uid);

        try {
            service.save(dto);
            flag = true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        map.put("flag", flag);

        return map;
    }

    //list
//    @GetMapping("/freeboard")
//    public List<FreeDTO> getAllFreeBoard() {
//        return service.getAll();
//    }

    //detail
    @GetMapping("/{num}")
    public Map getFreeBoard(@PathVariable int num) {
        Map map = new HashMap();
        FreeDTO dto = service.detail(num);
        map.put("dto", dto);

        return map;
    }

    //delete
    @DeleteMapping("/delete/{num}")
    public Map deleteBoard(@PathVariable int num, @RequestBody Map<String, String> req) {
        Map map = new HashMap();
        String check = req.get("check");

        boolean isDeletedBoard = service.deleteBoard(num, check);

        map.put("flag", isDeletedBoard);

        return map;
    }

    //edit
    @PutMapping("/edit/{num}")
    public Map updateBoard(@PathVariable int num, @RequestBody Map<String, String> req) {
        Map map = new HashMap();

        boolean isUpdatedBoard = service.updateBoard(num, req);

        map.put("flag", isUpdatedBoard);

        return map;
    }
}
