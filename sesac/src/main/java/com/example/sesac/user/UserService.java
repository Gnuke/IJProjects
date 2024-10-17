package com.example.sesac.user;

import com.example.sesac.auth.SecurityUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserDAO dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //sign-up
    @Transactional
    public void saveUser(UserDTO dto){
        dto.setJoinDate(LocalDateTime.now());
        dto.setRoles(Set.of(Role.User));
        dao.save( new User( dto.getUid(), passwordEncoder.encode(dto.getPwd()), dto.getEmail(), dto.getJoinDate(), dto.getRoles()));
    }

    //내 정보 조회
    public UserDTO getUser(Long id){
        User entity =dao.findById(id)
                        .orElseThrow(()->new RuntimeException("User not found"));
        if(entity != null){
            return new UserDTO( entity.getUid(), entity.getEmail(), entity.getJoinDate() );
        }
        return null;
    }

    // 비밀번호 확인 후 삭제
    @Transactional
    public boolean pwdCheckAndDelete(String check){
        Long id = SecurityUtil.getCurrentUserId();

        User user = dao.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 비밀번호 일치 여부 확인
        if (passwordEncoder.matches(check, user.getPwd())) {
            System.out.println("비밀번호가 일치합니다. 사용자 삭제.");
            dao.deleteById(id);  // 비밀번호가 맞으면 사용자 삭제
            return true;
        }

        return false;
    }
}
