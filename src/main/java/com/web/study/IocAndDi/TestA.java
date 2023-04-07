package com.web.study.IocAndDi;

import org.springframework.stereotype.Component;

@Component("t1")  //bean에 이름이 등록됨 bean의 기준은 맨앞 글자가 소문자로 나머지는 카멜표기법으로 //일반적으로 클래스명 사용
public class TestA implements Test {

	@Override
	public void printTest() {
		System.out.println("TestA 클래스!!!");
		
	}
	
}
