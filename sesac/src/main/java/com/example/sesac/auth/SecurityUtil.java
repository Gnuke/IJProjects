package com.example.sesac.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {
    public static UserDetailsImpl getCurrentUserDetails() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null && auth.getPrincipal() instanceof UserDetailsImpl){
            return (UserDetailsImpl) auth.getPrincipal();
        }else{
            throw new RuntimeException("SecurityUtil : 회원정보가 존재하지 않음");
        }
    }

    public static String getCurrentUserId() { return getCurrentUserDetails().getUid(); }
}
