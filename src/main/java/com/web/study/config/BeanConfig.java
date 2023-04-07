package com.web.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.web.study.IocAndDi.TestC;

@Configuration
public class BeanConfig { 

	@Bean  //라이브러리에서 가지고 온것들은 bean에 등록후 사용함
	public TestC testC() {  //메소드명이 컴포넌트 이름이 된다
		return new TestC();
	}
	
}
