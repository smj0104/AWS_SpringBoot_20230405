package com.web.study.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Aspect     
@Component  //ioc등록
@Slf4j
public class TimerAop {
	
	//private final Logger logger = LogManager.getLogger(TimerAop.class);  //apache의 logger import
	
	// 접근지정자 public은 생략 가능
	@Pointcut("execution(* com.web.study.controller..*.*(..))")  //메소드명이 중요  ..=하위의 모든것
	private void pointCut() { //@Pointcut = 어떤 클래스나 메소드를 실행할 때 특정 조건에 따라 실행 여부를 결정하게 해줌
		
	}
	@Pointcut("@annotation(com.web.study.aop.annotation.TimerAspect)")
	private void annotationPointCut() {}
	
	@Around("annotationPointCut()&&pointCut()")   //@Around = 메소드 실행 전후에 공통적으로 수행할 작업을 정의
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {  //spring한테 예외를 밀어버림		
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		//전처리
		Object logic = joinPoint.proceed(); //proceed = 메소드 호출

		//후처리
		
		stopWatch.stop();
		log.info("[ Time ] >>> {}.{}: {}초",
				joinPoint.getSignature().getDeclaringTypeName(),  //클래스명
				joinPoint.getSignature().getName(),				  //메소드명
				stopWatch.getTotalTimeSeconds()					 // 실행시간
				); 

		return logic;
	}
}
