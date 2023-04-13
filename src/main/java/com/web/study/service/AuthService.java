package com.web.study.service;

import com.web.study.dto.request.auth.LoginReqDto;
import com.web.study.dto.request.auth.RegisteUserReqDto;
import com.web.study.dto.response.auth.JwtTokenResponseDto;

public interface AuthService {
	
	public void registeUser(RegisteUserReqDto registeUserReqDto); //service는 controller에서 들어옴
	public void duplicatedUsername(RegisteUserReqDto registeUserReqDto);
	
	public JwtTokenResponseDto login(LoginReqDto loginReqDto);
	
}
