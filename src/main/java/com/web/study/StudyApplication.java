package com.web.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.web.study.IocAndDi.IocTest;
import com.web.study.IocAndDi.TestA;
import com.web.study.IocAndDi.TestB;
import com.web.study.IocAndDi.TestC;

@SpringBootApplication
public class StudyApplication { // ioc에 등록되어있어야함 @SpringBootApplication에 @ComponentScan이 들어있음

	public static void main(String[] args) {
		SpringApplication.run(StudyApplication.class, args);
		// iocAndDiTest();
	}

//	public static void iocAndDiTest() {
//		// Inversion of Control 제어의 역전
//		IocTest ioctest = new IocTest(new TestB());
//		ioctest.run();
//	}

	// @Component 스프링 프레임워크에서 객체를 생성하고 관리하기 위해서는 "@Component" 어노테이션을 사용하여 클래스를 스프링
	// 컨테이너에 등록해야 합니다. (등록)

	// @Autowired 생성자에서 사용하는 경우, 스프링은 해당 클래스를 생성할 때 자동으로 의존성을 주입하게 됩니다. (자동주입)
	// 필드에서 사용하는 경우, 스프링은 필드에 대한 의존성을 주입합니다.
	// 메소드에서 사용하는 경우, 해당 메소드가 호출될 때 의존성을 주입합니다.
	
	//생성가능 객체만 bean으로 등록가능
	
	//Component 종류 configuration controller service repository의 네가지 		configuration=설정을 다루는 bean(다른 bean들을 추가가능 나머지는 하나만)
}
