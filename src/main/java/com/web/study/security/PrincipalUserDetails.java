package com.web.study.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PrincipalUserDetails implements UserDetails {	//인터페이스를 반환할 수 없기에 구현해놓음


	private static final long serialVersionUID = -1913596722176708482L;
	
	private int userId;
	private String username;
	private String password;
	private List<String> roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		roles.forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role));
		});
		
		return authorities;
	}

	@Override
	public String getPassword() {	//필수getter
		return password;
	}

	@Override
	public String getUsername() {  //필수getter
		return username;
	}
	
	//사용기간 만료
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	//계정을 잠궈버림
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//비밀번호 5회 틀렸을시 
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//계정 비활성 상태(이메일 인증을 완료해야하거나, 전화번호 인증을 하지 않았을 때)
	@Override
	public boolean isEnabled() {
		return true;
	}

}
