package com.web.study.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.web.study.domain.entity.Authority;
import com.web.study.domain.entity.User;
import com.web.study.dto.request.auth.LoginReqDto;
import com.web.study.dto.request.auth.RegisteUserReqDto;
import com.web.study.dto.response.auth.JwtTokenResponseDto;
import com.web.study.exception.CustomException;
import com.web.study.repository.UserRepository;
import com.web.study.security.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {  //컨트롤러한테 다시 jwt토큰리턴 ,컨트롤러가 클라이언트한테 응답

	private final UserRepository userRepository;
	private final AuthenticationManagerBuilder authenticationManagerBuilder; 
	private final JwtTokenProvider jwtTokenProvider;

	@Override
	public void registeUser(RegisteUserReqDto registeUserReqDto) {
		User userEntity = registeUserReqDto.toEntity();
		
		userRepository.saveUser(userEntity); //insert user_mst  repositort를 통해 mybatis로 갔다오면 userEntity안에 userid가 들어감
		
		List<Authority> authorities  = new ArrayList<>();
		authorities.add(Authority.builder().user_id(userEntity.getUser_id()).role_id(1).build());
		
		userRepository.addAuthorities(authorities);
		
	}

	@Override
	public void duplicatedUsername(RegisteUserReqDto registeUserReqDto) {
		User userEntity = userRepository.findUserByUsername(registeUserReqDto.getUsername());
		
		if(userEntity != null) {  //유저를 검색해서 가져왔을때 null이 아닐경우
			Map<String, String> errorMap = new HashMap<>();
			errorMap.put("username", "이미 사용중인 사용자이름입니다.");
			
			throw new CustomException("중복 검사 오류", errorMap);
		}
	}

	@Override
	public JwtTokenResponseDto login(LoginReqDto loginReqDto) {
		
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(loginReqDto.getUsername(), loginReqDto.getPassword());	
		
		//UserDetailsService의 loadUserByUsername() 호출이 된다  Authentication = //로그인에 대한 처리   authenticationManagerBuilder.getObject()호출이 authenticationmanager객체를 불러옴
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);	//로그인 정보를 authenticaion 안에서 관리 //내부적인거라 뜯어봐도 뭐 없다
				//provider의 createtoken에 넣어준다
		return jwtTokenProvider.createToken(authentication);
	}
	
}
