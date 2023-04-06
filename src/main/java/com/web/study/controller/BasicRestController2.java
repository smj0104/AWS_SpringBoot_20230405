package com.web.study.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ErrorResponseDto;
import com.web.study.dto.ResponseDto;
import com.web.study.dto.request.Fruit;


@RestController
public class BasicRestController2 {
	
	@GetMapping("/colors")
	public ResponseEntity<? extends ResponseDto> colors(Fruit fruit) {
		String fruitabc = fruit.getFruit();
		return ResponseEntity.ok().body(DataResponseDto.of(fruitabc));
	}
	
	@GetMapping("/read3/{name}")
	public ResponseEntity<? extends ResponseDto> read3(@PathVariable String name){
		Map<String, String> infoMap = new HashMap<>();
		
		infoMap.put("a", "M");
		infoMap.put("b", "M1");
		infoMap.put("c", "M2");
		infoMap.put("d", "M3");
		infoMap.put("e", "M4");
		
		return ResponseEntity.ok().body(DataResponseDto.of(infoMap.get(name)));
	}
	
	@GetMapping("/read4")
	public ResponseEntity<? extends ResponseDto> read4(Fruit fruit){
		
		String leftFruit = fruit.getFruit() + "(" + fruit.getLeft() + ")" + fruit.getOrigin();
		
		return ResponseEntity.ok().body(DataResponseDto.of(leftFruit));
	}
	
	@PostMapping("/fruitlist")
	public ResponseEntity<? extends ResponseDto> fruitlist(@RequestBody Fruit fruit){
		System.out.println("과일추가완료");
		return ResponseEntity.created(null).body(DataResponseDto.of(fruit));
	}
	
	
	@GetMapping("/fruitbucket")
	public ResponseEntity<? extends ResponseDto> fruitbucket(String fruit) {
		List<String> fruitList = new ArrayList<>();
		fruitList.add("apple");
		fruitList.add("pear");
		fruitList.add("banana");
		fruitList.add("1");
		fruitList.add("2");
		
		if(fruitList.contains(fruit) ) {
			return ResponseEntity.ok().body(DataResponseDto.of(fruitList));
		}else {
			return ResponseEntity.badRequest().body(ErrorResponseDto.of(HttpStatus.INTERNAL_SERVER_ERROR));
		}
		
	}
	
}
