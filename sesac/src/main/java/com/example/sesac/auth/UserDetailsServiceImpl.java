package com.example.sesac.auth;

import com.example.sesac.user.User;
import com.example.sesac.user.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByUid(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with uid : " + username));

        return UserDetailsImpl.build(user);
    }
}
