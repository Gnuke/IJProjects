package com.example.sesac.auth;


import com.example.sesac.user.UserDAO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) {
        LOGGER.info("Load user by username: {}", username);
        return userDAO.getByUid(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
