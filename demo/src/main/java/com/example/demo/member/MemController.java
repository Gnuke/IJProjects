package com.example.demo.member;

import com.example.demo.auth.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/member")
public class MemController {
    @Autowired
    private MemService memService;

    @Autowired
    private TokenProvider provider;

    @Autowired
    private AuthenticationManagerBuilder abuilder;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 가입
    @PostMapping("/join")
    public Map join(@RequestBody MemDTO m) {
        Map map = new HashMap();

        //System.out.println("###" + m.getId());
        boolean flag = true;
        try {
            memService.save(m);
            map.put("member", m);
        } catch (Exception e) {
            flag = false;
            throw new RuntimeException(e);
        }

        map.put("flag", flag);
        return map;
    }

    // login
//    @PostMapping("/login")
//    public Map login(@RequestBody MemDTO m) {
//        MemDTO myInfo = memService.myInfo(m.getId());
//        Map map = new HashMap();
//        boolean flag = true;
//
//        if(myInfo != null) {
//            if(passwordEncoder.matches(m.getPwd(), myInfo.getPwd())){
//                UsernamePasswordAuthenticationToken authtoken =
//                        new UsernamePasswordAuthenticationToken(m.getId(), m.getPwd());
//                Authentication auth =
//                        abuilder.getObject().authenticate(authtoken);
//                MemDTO authUserInfo = memService.myInfo(auth.getName());
//                String token = provider.getToken(myInfo);
//                //System.out.println("login 했을 때 user : " + auth.getName());
//                map.put("token", token);
//            }else{
//                // 비밀번호 불일치
//                flag = false;
//            }
//        }else{
//            map.put("myInfo", null);
//        }
//
//        map.put("flag", flag);
//
//        return map;
//    }
}
