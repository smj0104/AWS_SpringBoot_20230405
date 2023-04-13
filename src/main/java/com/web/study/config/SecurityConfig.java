package com.web.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean  //컴포넌트를 달수없을때 사용
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	//security filter
	@Override
	protected void configure(HttpSecurity http) throws Exception {  //controller 들어오기전 한번 거친다
		http.csrf().disable();	//csrf = 토큰
		http.authorizeRequests()
			.antMatchers("/auth/register/**", "/auth/login/**")  //antMatchers = 어떤 URL을 어떤 필터로 보호해야 하는지 정의하는 데 사용
			.permitAll()
			.anyRequest()
			.authenticated();
	}
}
