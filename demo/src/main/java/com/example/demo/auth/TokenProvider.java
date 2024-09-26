package com.example.demo.auth;

import com.example.demo.member.MemDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor // 필요한 멤버 받는 생성자 자동 생성
@Component
public class TokenProvider {
	// 토큰 유효 시간.
	private final long expiredTime = 1000 * 60 * 60 * 1L; // 1시간
	
	//암호화에 사용할 키 생성
	Key key = Keys.hmacShaKeyFor(
			"testtokentesttokentesttokentesttokentesttokentesttokentesttoken"
			.getBytes(StandardCharsets.UTF_8));
	
	private final UserDetailsService service;
	
	// 토큰 생성하여 반환
	public String getToken(Authentication authentication) {
		return Jwts.builder().setSubject(authentication.getName()) // 토큰의 제목
				.setHeader(createHeader()) // 토큰의 헤더 정보 셋
				.setClaims(createClaims((MemDTO) authentication)) // 클레임 정보 셋
				.setExpiration(new Date((new Date()).getTime() + expiredTime))
				.signWith(key, SignatureAlgorithm.HS256).compact();
	}
	
	// 헤더 정보 생성해서 반환. 토큰의 종류, 암호화 알고리즘 종류, 등록 시간...
	public Map createHeader() {
		Map<String, Object> map = new HashMap();
		map.put("type", "JWT");
		map.put("alg", "HS256");
		map.put("regDate", System.currentTimeMillis());
		return map;
	}
	
	// 클레임 생성해서 반환. 인증자와 관련된 정보 등록
	public Map createClaims(MemDTO dto) {
		Map<String, Object> map = new HashMap();
		map.put("username", dto.getId());
		return map;
	}
	
	// 클레임 바디 반환
	private Claims getClaims(String token) {
		return (Claims) Jwts.parserBuilder().setSigningKey(key).build()
				.parse(token).getBody();
	}
	
	//username 반환
	public String getUserName(String token) {
		return (String)getClaims(token).get("username");
	}
	
//	//role 반환
//	public String getRole(String token) {
//		return (String)getClaims(token).get("roles");
//	}
	
	// 요청 헤더에서 토큰을 꺼내 반환
	public String resolveToken(HttpServletRequest req) {
		return req.getHeader("token");
	}
	
	// 토큰 유효성 검사
	public boolean validateToken(String token) {
		try {
			Claims claims = getClaims(token);
			return !claims.getExpiration().before(new Date());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	// 토큰 인증
	public Authentication getAuthenticate(String token) {
		UserDetails user = service.loadUserByUsername(getUserName(token));
		return new UsernamePasswordAuthenticationToken(
				user, "", user.getAuthorities());
	}
	
}
