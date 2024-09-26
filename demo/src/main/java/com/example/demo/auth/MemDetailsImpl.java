package com.example.demo.auth;

import com.example.demo.member.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
public class MemDetailsImpl implements UserDetails{
	private final Member m;
	
	// user의 권한 정보 설정
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<GrantedAuthority> list = new ArrayList<>();
		list.add(new SimpleGrantedAuthority(m.getId()));
		
		return list;
	}
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return m.getPwd();
	}
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return m.getId();
	}

	// 사용 가능 여부에 대한 정보들을 알려주는 메서드들-----------------------------------------------------------
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		// return UserDetails.super.isAccountNonExpired();
		return true; // 모두 허용
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		// return UserDetails.super.isAccountNonLocked();
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		// return UserDetails.super.isCredentialsNonExpired();
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		// return UserDetails.super.isEnabled();
		return true;
	}

}
