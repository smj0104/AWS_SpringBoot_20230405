package com.web.study.dto.response.auth;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JwtTokenResponseDto {
	private String grantType;
	private String accessToken;
}
