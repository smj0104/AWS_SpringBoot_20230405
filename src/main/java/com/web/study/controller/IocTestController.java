package com.web.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.IocAndDi.IocTest;
import com.web.study.IocAndDi.IocTest2;
import com.web.study.IocAndDi.TestA;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class IocTestController {
// @RequiredArgsConstructor 안쓸경우 autowired를 각각 사용
//	@Autowired
//	private IocTest iocTest;
//	@Autowired
//	private IocTest2 iocTest2;
//	
	
	private final IocTest iocTest;
	private final IocTest2 iocTest2;
	
	
	
//	public IocTestController(IocTest iocTest, IocTest2 iocTest2) {
//		this.iocTest = iocTest;
//		this.iocTest2 = iocTest2;
//	}
//	component,bean 둘 다 같은 의미
// 일반 자바 객체와 bean의 차이 = IOC에 등록이 되어있느냐 (등록이 되어있으면 bean)
	@GetMapping("/ioc/test")
	public Object test() {
		iocTest.run();
		iocTest2.run();
		return null;
	}
}
