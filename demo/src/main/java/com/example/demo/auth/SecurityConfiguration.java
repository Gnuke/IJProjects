package com.example.demo.auth;

import com.example.demo.member.MemDTO;
import com.example.demo.member.MemService;
import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration {
	private final TokenProvider provider;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	// 보안 관련 설정 메서드
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.httpBasic(HttpBasicConfigurer::disable) // 기본 설정 disable
				.csrf(CsrfConfigurer::disable) // 쓰기 작업 막은 것을 해제, post, put ... 요청 가능
				.cors(Customizer.withDefaults()) // 네트워크 도메인, ip 허용 코드 작성한 내용 적용
				.authorizeHttpRequests((authz) -> authz
						// forward 요청 모두 허용
						.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
						.requestMatchers("/","member/join", "member/login", "/error", "/read-img/**").permitAll()
						.requestMatchers("/auth/**", "/board/**").authenticated()
						.anyRequest().permitAll()
				)
				.addFilterBefore(new JwtAuthenticationFilter(provider), UsernamePasswordAuthenticationFilter.class);
		// 세션 사용 안함 설정
		http.sessionManagement(configurer -> configurer.sessionCreationPolicy(
				SessionCreationPolicy.STATELESS));
		return http.build();
	}
}
