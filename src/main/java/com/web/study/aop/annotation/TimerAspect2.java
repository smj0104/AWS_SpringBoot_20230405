package com.web.study.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD}) 
public @interface TimerAspect2 {
	//ResponseEntity<	>  HTTP 통신 DB가 중요
}
