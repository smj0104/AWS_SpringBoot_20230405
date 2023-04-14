package com.web.study.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean { //IOC에 등록안됨 new로 사용

	private final JwtTokenProvider jwtTokenProvider;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String token = getToken(request);
		
		System.out.println("token: " + token);
		
		boolean validationFlag = jwtTokenProvider.validateToken(token);
		
		if(validationFlag) {
			Authentication authentication = jwtTokenProvider.getAuthentication(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		chain.doFilter(request, response);  //없으면 필터에서 나가지못함 (필터에서 필터로 넘어가는 역할)
	}
	
	private String getToken(ServletRequest request) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String type = "Bearer";
		String token = httpServletRequest.getHeader("Authorization");
		
		//hasText 문자열이 Null,공백이 아닌지 확인
		if(StringUtils.hasText(token) && token.startsWith(type)) {		//문자열이 있어하고 Bearer이 있어야한다				Bearer jwt사용시의 약속	
			return token.substring(type.length() + 1); //앞에 붙은 Bearer을 잘라냄
		}
		return null;
	}

}
