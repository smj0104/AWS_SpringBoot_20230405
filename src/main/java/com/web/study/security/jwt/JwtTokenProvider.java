package com.web.study.security.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.web.study.dto.response.auth.JwtTokenResponseDto;
import com.web.study.security.PrincipalUserDetails;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

	private final Key key;
	
	public JwtTokenProvider(@Value("${jwt.secretKey}") String secretKey) {  //@component일때 사용가능
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}
	
	public JwtTokenResponseDto createToken(Authentication authentication) {
		StringBuilder authoritiesBuilder = new StringBuilder();
		
		authentication.getAuthorities().forEach(grantedAuthority -> {
			authoritiesBuilder.append(grantedAuthority.getAuthority());
			authoritiesBuilder.append(",");
		});
		
		authoritiesBuilder.delete(authoritiesBuilder.length() - 1, authoritiesBuilder.length());
		
		String authorities = authoritiesBuilder.toString();
		
		long now = (new Date()).getTime();  //단위가 커서 long으로 잡아준다
		//1000 ==  1초
		Date tokenExpiresDate = new Date(now + (1000 * 60 * 30));  //토큰 만료 시간
		
		PrincipalUserDetails userDetails = (PrincipalUserDetails) authentication.getPrincipal();
		
		String accessToekn = Jwts.builder()
				.setSubject(authentication.getName())
				.claim("userId", userDetails.getUserId())
				.claim("auth", authorities)
				.setExpiration(tokenExpiresDate)
				.signWith(key,SignatureAlgorithm.HS256)
				.compact();
				
		return JwtTokenResponseDto.builder()
				.grantType("Bearer")
				.accessToken(accessToekn)
				.build();
	}
}
