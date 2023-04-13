package com.web.study.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.web.study.domain.entity.User;
import com.web.study.exception.CustomException;
import com.web.study.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {  //security관련 service

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {  //로그인하고자하는 이름이 들어옴 userdetails가 정보를 담고 authentication이 저장
		User userEntity = userRepository.findUserByUsername(username);
		
		if(userEntity == null) {
			 throw new CustomException("사용자 정보를 다시 확인해주세요");
		}
		
		return userEntity.toPrincipal();
	}

}
