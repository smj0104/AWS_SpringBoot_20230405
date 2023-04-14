package com.web.study.security.jwt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.study.dto.ErrorResponseDto;      

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpStatus.UNAUTHORIZED.value());  //401에러
		PrintWriter out = response.getWriter();
		ObjectMapper responseJson = new ObjectMapper();
		
		out.println(responseJson.writeValueAsString(ErrorResponseDto.of(HttpStatus.UNAUTHORIZED, authException)));//401 전달
	}

	
	/**
	 * Bearer(token)으로 발금
	 * localstorage에 등록
	 * 
	 * 요청이 들어오면 스레드가 생성
	 * 시큐리티 필터로 들어감
	 * 로그인,회원가입은 security context에 기록이 없어도됨
	 * antmatcher는 hasrole(특정권한만 요청을 날릴수있음)이 걸린 객체는 authenticatin만으론 안됨.
	 *
	 * 1.회원가입
	 * 		1.회원가입할 정보를 입력
	 *		2.해당 정보로 회원가입 요청을 보낸다.
	 *		3.회원가입 정보(password는 암호화)를 DB에 저장한다.
	 *		
	 *	toENtity때 암호화
	 *
	 * 2. 로그인
	 *		1.로그인할 정보를 입력(username,password)
	 *		2.해당 정보로 로그인 요청을 보낸다.
	 *		3.AuthenticationManager에게 username,password를 전달한다.
	 *		4.AuthenticationManager가 인증을 처리한다.
	 *		5.UserDetailsService의 loadUserByUsername(String name)이 호출된다.
	 *			UserDetails를 리턴받아서 Authentication객체를 생성하기 위함.
	 *			이 때 해당 username으로 DB에서 조회된 UserEntity가 없으면 등록되지 않은 회원임.(예외처리)  5번까지 security인증
	 *			Authentication객체가 생성되면 로그인 성공.
	 *		6.Authentication 객체를 JWT로 변환하는 작업을 해야함.
	 *		7.변환된 JWT를 클라이언트에게 응답.
	 *		8.클라이언트는 JWT토큰을 로컬스토리지나 쿠키에 저장.
	 *
	 * 3. 요청시 토큰 인증
	 * 		1.요청 Header에 Bearer 방식으로 JWT 토큰을 전달한다.
	 * 		2.스프링 시큐리티에서 인증이 필요한 요청들은 JwtAuthenticationFilter를 통해 인증절차를 진행한다.
	 * 			이 때 인증의 최종 목표는 SecurityContextHolder객체의 Context에 Authentication을 등록하는 것이다.
	 * 			Authentication 객체가 등록이 되어 있으면 인증이 된 것이다.
	 * 		3.JwtAuthenticatinFilter에서 요청 Header에 들어있는 Authorization의 JWT토큰을 추출한다.
	 * 		4.JWT토큰을 검증한다.
	 * 			이 때 검증에 실패하여 Exception이 생성되면 AuthenticationEntryPoint가 실행되면서 401 응답을 하게된다.
	 * 		5.JWT토큰 검증이 완료되면 JWT 토큰에서 Claims를 추출한다.
	 * 		6.Claims에서 username과 Authorities를 추출하여 Authentication 객체를 생성한다.
	 * 		7.생성된 Authentication객체를 SecurityContext에 등록한다.
	 * 		8.등록이 완료되면 다음 doChain이 호출된다. 
	 * 
	 * 
	 */
}
