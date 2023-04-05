package com.web.study.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ErrorResponseDto;
import com.web.study.dto.ResponseDto;

@RestController  //controller에 responsebody를 항상 붙인 느낌  view를 리턴하고싶을시 controller 사용
public class BasicController {  //restcontroller 데이터 응답 용도 controller view를 리턴하는 용도
	
	
	//@ResponseBody  //없으면 view resolve한테 보냄
	@GetMapping("/view/test")  //requestMapping=옛날버젼
	public ResponseEntity<? extends ResponseDto> view() {  //string일때만 text 그외는 전부 json으로 응답
								//ResponseDto를 상속받았으면 사용가능
		List<String> strList = new ArrayList<>();
		strList.add("a");
		strList.add("a1");
		strList.add("a2");
		strList.add("a3");
		
		if(strList.contains("a")) {
			try {
				throw new RuntimeException("오류");
			}catch (Exception e) {
				return ResponseEntity.badRequest().body(ErrorResponseDto.of(HttpStatus.INTERNAL_SERVER_ERROR, e));				
			}
		}
		
		return ResponseEntity.ok().body(DataResponseDto.of(strList));  //응답 인터페이스
	}
}

/**
 * get요청
 * param1개
 * param 여러개 Dto만들어서 하나, Dto없이 하나
 * 
 * post요청
 * param 여러개 formData로 하나 JSON으로 하나
 * 
 * 
 * 과일바구니
 * 
 */


