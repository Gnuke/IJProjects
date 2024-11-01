package com.example.sesac.pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PaginationController {

    @Autowired
    public PaginationService paginationService;

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
}
