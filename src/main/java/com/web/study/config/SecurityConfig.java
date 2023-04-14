package com.web.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.web.study.security.jwt.JwtAuthenticationEntryPoint;
import com.web.study.security.jwt.JwtAuthenticationFilter;
import com.web.study.security.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final JwtTokenProvider jwtTokenProvider;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Bean  //컴포넌트를 달수없을때 사용
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	//security filter
	@Override
	protected void configure(HttpSecurity http) throws Exception {  //controller 들어오기전 한번 거친다
		http.csrf().disable();	//csrf = 토큰
		http.httpBasic().disable();	// 웹 기본 인증 방식
		http.formLogin().disable();	// 폼태그를 통한 로그인 보통 mvc쓸때 사용
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);  //세션 비활성(무상태성)
		http.authorizeRequests()
			.antMatchers("/auth/register/**", "/auth/login/**")  //antMatchers = 어떤 URL을 어떤 필터로 보호해야 하는지 정의하는 데 사용
			.permitAll()
			.antMatchers("/courses")
			.hasRole("ADMIN")
			.anyRequest()
			.authenticated() 
			.and()
			.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)  //시큐리티 인증 이전에 필터를 하나 넣겠다 (이 필터가 인증을 처리함)  2번째는 고정으로 들어감
			.exceptionHandling()
			.authenticationEntryPoint(jwtAuthenticationEntryPoint);  //필터    로그인 안될시 받아서 예외처리(401error 인증되지않았다)
			;
	}
}
