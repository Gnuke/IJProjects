package com.example.demo.auth;

import com.example.demo.member.MemDAO;
import com.example.demo.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MemDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private MemDAO dao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Member m = dao.findById(username)
				.orElseThrow( 
						() -> new UsernameNotFoundException("not found username " + username)
				);
		System.out.println( "userdetails service : " + m);
		return new MemDetailsImpl(m);
	}

}
