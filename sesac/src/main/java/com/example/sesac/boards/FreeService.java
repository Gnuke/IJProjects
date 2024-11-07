package com.example.sesac.boards;

import com.example.sesac.auth.SecurityUtil;
import com.example.sesac.user.User;
import com.example.sesac.user.UserDAO;
import com.example.sesac.user.UserDTO;
import com.example.sesac.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FreeService {
    @Autowired
    private FreeDAO dao;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(FreeDTO dto) {
        try {
            UserDTO userDTO = userService.getUser(dto.getUid());
            // dto에서 uid를 사용하여 User 엔티티 조회
            User user = new User( userDTO.getUid(), null, null, null, null );

            // 조회된 User 엔티티와 dto의 제목, 내용으로 Free 엔티티 생성
            Free free = new Free(user, dto.getTitle(), dto.getContent());

            // dao를 통해 free 엔티티 저장
            dao.save(free);
        } catch (Exception e) {
            throw new RuntimeException("Error saving Free entity", e);
        }
    }


    //Detail
    public FreeDTO detail(int num) {
        Free entity = dao.findById(num).orElse(null);
        if(entity != null){
            return new FreeDTO(entity.getNum(), entity.getUid(), entity.getTitle(), entity.getContent(), entity.getWDate());
        }
        return null;
    }

    // 비밀번호 확인 후 삭제
    @Transactional
    public boolean deleteBoard(int num, String check) {
        String uid = SecurityUtil.getCurrentUserId();

        User user = userDAO.findByUid(uid)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(passwordEncoder.matches(check, user.getPwd())){
            dao.deleteById(num);
            return true;
        }

        return false;
    }

    //비밀번호 확인 후 수정
    @Transactional
    public boolean updateBoard(int num, Map<String, String> req) {
        String uid = SecurityUtil.getCurrentUserId();

        User user = userDAO.findByUid(uid)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String check = req.get("check");
        String title = req.get("title");
        String content = req.get("content");

        if(passwordEncoder.matches(check, user.getPwd())){
            dao.findById(num).ifPresent(entity -> {
                entity.setTitle(title);
                entity.setContent(content);

                dao.save(entity);
            });
            return true;
        }
        return false;
    }
}
