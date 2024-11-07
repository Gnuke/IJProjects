package com.example.sesac.pagination;

import com.example.sesac.auth.SecurityUtil;
import com.example.sesac.boards.FreeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PaginationController {

    @Autowired
    public ListService paginationService;
    @Autowired
    private WriteService writeService;

    @GetMapping("/{boardId}")
    @ResponseBody
    public Page getBoardALl(@PathVariable String boardId, @RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of((page - 1), size, Sort.by("num").descending());

        return switch( boardId ){
            case "freeboard" -> paginationService.getFreeBoardPage(pageable);
            default -> throw new IllegalArgumentException("유효하지 않은 boardId입니다: " + boardId);
        };
    }

    @PostMapping("/{boardId}/write")
    public Map write(@PathVariable String boardId, @RequestBody Map<String, String> req){

        Map<String, Object> res = new HashMap<>();
        boolean flag = false;

        String userId = req.get("userId");
        String uid = SecurityUtil.getCurrentUserId();

        if( userId.equals(uid) ) {
            switch (boardId) {
                case "freeboard" -> {
                    FreeDTO freeDTO = new FreeDTO();
                    freeDTO.setUid(userId);
                    freeDTO.setTitle(req.get("title"));
                    freeDTO.setContent(req.get("content"));

                    writeService.saveFreeBoard(freeDTO);
                    flag = true;
                }
                default -> throw new IllegalArgumentException("유효하지 않은 boardId입니다: " + boardId);
            }
        }
        res.put("flag", flag);

        return res;
    }

}
