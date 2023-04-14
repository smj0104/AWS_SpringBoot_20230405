package com.web.study.security.jwt;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.web.study.dto.response.auth.JwtTokenResponseDto;
import com.web.study.exception.CustomException;
import com.web.study.security.PrincipalUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class JwtTokenProvider {  

	private final Key key;	//ioc를 컴포넌트에 등록하며 key생성
	
	public JwtTokenProvider(@Value("${jwt.secretKey}") String secretKey) {  //@component일때 사용가능
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}
	
	public JwtTokenResponseDto createToken(Authentication authentication) {	//안에서 토큰을 생성 	Authentication에 인증성공한 유저의 정보가 들어있다 authroities가 들어가있음
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
	
	public boolean validateToken(String token) {	//토큰을 받아서 사용가능한지 확인함
		try {
			Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token);
			
			return true;
		} catch (SecurityException | MalformedJwtException e) {  //bearer를 안때고 넣을때
			// Security라이브러리에 오류가 있거나, JSON의 포맷이 잘못된 형식의 JWT가 들어왔을 때 예외
			// SignatureException이 포함되어 있다.
			log.info("Invalid JWT Token", e);
		} catch (ExpiredJwtException e) {
			// 토큰의 유효기간이 만료된 경우의 예외
			log.info("Expired JWT Token", e);
		} catch (UnsupportedJwtException e) {
			// jwt의 형식을 지키지 않은 경우 (Header.Paylord.Signature) 이 형식을 지키지않았을때
			log.info("Unsupported JWT Token", e);
		} catch (IllegalArgumentException e) {
			// JWT토큰이 없을 때
			log.info("IllegalArgument JWT Token", e);		
		}
		 catch (Exception e) {
			 log.info("JWT Token error", e);		
		}
		
		return false;  //  == 쓸수없는 토큰이다
	}
	
	public Authentication getAuthentication(String accessToken) {	//authentication객체로 변환
		Claims claims = parseClaims(accessToken);  //Claims에 getter setter가 이미 존재
		Object roles = claims.get("auth").toString();
		
		if(roles == null) {
			throw new CustomException("권한 정보가 없는 토큰입니다.");
		}
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		String[] rolesArray = roles.toString().split(",");
		
		Arrays.asList(rolesArray).forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role));
		});
		
		UserDetails userDetails = new User(claims.getSubject(),"", authorities);
		
		return new UsernamePasswordAuthenticationToken(userDetails,"",authorities);
		}
	
	private Claims parseClaims(String accessToken) {		//안에 든 claime들을 꺼냄
		try {
			return Jwts.parserBuilder()
					.setSigningKey(key)
					.build()
					.parseClaimsJws(accessToken)
					.getBody(); //이 부분이 payload(Body)
		} catch (ExpiredJwtException e) {
			return e.getClaims();
		}
		
	}
	
}
