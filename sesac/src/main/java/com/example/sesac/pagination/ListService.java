package com.example.sesac.pagination;

import com.example.sesac.boards.Free;
import com.example.sesac.boards.FreeDAO;
import com.example.sesac.boards.FreeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListService {
    private final FreeDAO freeDAO; // 필요에 따라 repository 추가

    public ListService(FreeDAO freeDAO) { // 필요에 따라 dao 변수 추가
        this.freeDAO = freeDAO;
    }

    public Page<FreeDTO> getFreeBoardPage(Pageable pageable) {
        Page<Free> freeList = freeDAO.findAll(pageable);

        return freeList.map(free -> new FreeDTO(
                free.getNum(),
                free.getUid(),
                free.getTitle(),
                free.getContent(),
                free.getWDate()
        ));
    }


}
