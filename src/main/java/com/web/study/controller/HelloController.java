package com.web.study.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class HelloController { //Controller = 요청에 따라 다른 응답을 함 (재사용이 목적) 

	@GetMapping("/hello") //이 주솔 get요청
	public Map<String, String> hello(String name) {
		
		Map<String, String> testMap = new HashMap<>();
		testMap.put("name", name);
		testMap.put("age", "30");
		testMap.put("address", "부산 동래구 사직동");
		
		return testMap;
	}
}
