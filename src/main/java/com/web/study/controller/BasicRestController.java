package com.web.study.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ResponseDto;
import com.web.study.dto.request.BasicTestDto;

import lombok.Data;

@Data
class Params {
	private int age;
	private String name;
}

@RestController
public class BasicRestController {

	// GET 요청의 param을 처리하는 방법
	@GetMapping("/read")    //상속받은 객체만 사용(data,errorResponseDto)   변수가 4개정도 넘어가면 Dto로 묶음
	public ResponseEntity<? extends ResponseDto> read(BasicTestDto basicTestDto) {  //@requestparm을 생략하려면 key값이 변수명이 오면 된다.
		
		String userInfo = basicTestDto.getName() + "(" + basicTestDto.getAge() + ")";
		
		return ResponseEntity.ok().body(DataResponseDto.of(userInfo)); //body가 제네릭
	}
	
	@GetMapping("/read2/{id}")
	public ResponseEntity<? extends ResponseDto> read2(@PathVariable int id) {  //pathvariable == post put 전부 가능
		Map<Integer, String> userMap = new HashMap<>();
		
		userMap.put(1, "김준1");
		userMap.put(2, "김준2");
		userMap.put(3, "김준3");
		userMap.put(4, "김준4");
		userMap.put(5, "김준5");
		
		
		return ResponseEntity.ok().body(DataResponseDto.of(userMap.get(id)));
		
	}
	
	//post요청의 데이터 처리
	@PostMapping("/create")
	public ResponseEntity<? extends ResponseDto> create(@RequestBody BasicTestDto basicTestDto) {  //json받을시에 @requestbody 필요
		System.out.println("데이터 추가");
		
		return ResponseEntity.created(null).body(DataResponseDto.of(basicTestDto)); //Dto를 바로 JSON으로 바꿔줌
	}							//created에는 uri를 넣어줄수있음(어디로 넘어갈지같은 정보)
	
	
	
	
	
}//return자료형으로 ResponseDto를 쓰는 이유 :내가 응답하는 데이터의 상태를 설정해주기위해(상태코드,헤더,바디 수정등)
	