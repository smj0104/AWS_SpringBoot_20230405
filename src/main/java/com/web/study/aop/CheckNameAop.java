package com.web.study.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class CheckNameAop {
	@Pointcut("execution(* com.web.study.controller.lecture.StudentController.*(..))")
	private void PointCut() {}
	
	@Pointcut("@annotation(com.web.study.aop.annotation.CheckNameAspect)")
	private void annotationPointCut() {}
	
	@Around("annotationPointCut()&&PointCut()")
	public Object check(ProceedingJoinPoint joinPoint) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		Object logic = joinPoint.proceed();
		
		stopWatch.stop();
		log.info(" [ name ] >>> {}:{}",
				joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName()
				);
		
		return logic;
	}
}
