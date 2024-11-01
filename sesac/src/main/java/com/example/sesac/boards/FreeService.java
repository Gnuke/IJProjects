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

    //게시글 생성
    public void save(FreeDTO dto) {
        dao.save( new Free(dto.getUdtos(), dto.getTitle(), dto.getContent()));
    }
    //write json 형식으로 보내는데 이 때 User 타입으로 반환이 안됨 처리 어떻게 해야할 지 모르겠음

    //전체 목록 조회
    public List<FreeDTO> getAll(){
        Sort sort = Sort.by(Sort.Direction.DESC, "num");
        List<Free> l = dao.findAll(sort);

        // 엔티티 -> DTO로 변환
        return l.stream()
                .map(free -> new FreeDTO(free.getNum(), free.getUid(), free.getTitle(), free.getContent(), free.getWDate()))
                .collect(Collectors.toList());
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
