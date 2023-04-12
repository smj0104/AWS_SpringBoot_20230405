package com.web.study.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)  //해당 메소드 실행시 anotation실행시켜라
@Target({ElementType.METHOD})  //메소드에 다는 anotation이라는걸 명시
public @interface TimerAspect {

}
