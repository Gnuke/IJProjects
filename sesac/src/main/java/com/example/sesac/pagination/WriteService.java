package com.example.sesac.pagination;

import com.example.sesac.auth.SecurityUtil;
import com.example.sesac.boards.Free;
import com.example.sesac.boards.FreeDAO;
import com.example.sesac.boards.FreeDTO;
import com.example.sesac.user.User;
import com.example.sesac.user.UserDTO;
import com.example.sesac.user.UserService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WriteService {
    private final FreeDAO freeDAO; // 필요에 따라 repository 추가
    private final UserService userService;

    public WriteService(FreeDAO freeDAO, UserService userService) { // 필요에 따라 dao 변수 추가
        this.freeDAO = freeDAO;
        this.userService = userService;
    }

    public void saveFreeBoard(FreeDTO freeDTO) {
        try{
            UserDTO userDTO = userService.getUser(freeDTO.getUid());

            User user = new User(userDTO.getUid(), null, null, null, null);

            Free free = new Free(user, freeDTO.getTitle(), freeDTO.getContent());

            freeDAO.save(free);

        } catch (Exception e) {
            throw new RuntimeException("Error saving Free entity", e);
        }
    }
}
