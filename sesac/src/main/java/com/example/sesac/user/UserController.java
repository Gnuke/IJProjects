package com.example.sesac.user;

import com.example.sesac.auth.JwtTokenProvider;
import com.example.sesac.auth.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/member")
public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    //signup
    @PostMapping("/signup")
    public Map signup(@RequestBody UserDTO dto) {
        boolean flag = true;
        Map map = new HashMap();
        try {
            service.saveUser(dto);
            map.put("dto", dto);
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }
        map.put("flag", flag);

        return map;
    }

    @PostMapping("/signin")
    public Map signin(@RequestBody UserDTO dto) {
        Map map = new HashMap();

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            dto.getUid(),
                            dto.getPwd()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtTokenProvider.generateToken(authentication);

            map.put("token", jwt);
            map.put("flag", true);
        } catch (AuthenticationException e) {
            map.put("flag", false);
        }

        return map;
    }

    // 탈퇴 시 비밀번호 확인 후 삭제
    @PostMapping("/check-password")
    public Map checkPassword(@RequestBody Map<String, String> req) {
        Map<String, Object> response = new HashMap<>();
        String check = req.get("check");

        // 서비스에서 비밀번호 확인 후 삭제 처리
        boolean isDeleted = service.pwdCheckAndDelete(check);

        response.put("flag", isDeleted);
        return response;
    }

    // 내 정보 조회
    @GetMapping("/myInfo")
    public Map myInfo() {
        Map map = new HashMap<>();
        Long id = SecurityUtil.getCurrentUserId();

        UserDTO dto = service.getUser(id);
        map.put("dto", dto);


        System.out.println("값 : " + dto);

        return map;
    }
}
