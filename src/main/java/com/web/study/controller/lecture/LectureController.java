package com.web.study.controller.lecture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ResponseDto;
import com.web.study.dto.request.lecture.LectureReqDto;
import com.web.study.service.LectureService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LectureController {

	private final LectureService lectureService;
	
	@PostMapping("/lecture")
	public ResponseEntity<? extends ResponseDto> registeLecture(@RequestBody LectureReqDto lectureReqDto) {
		System.out.println(lectureReqDto);
		lectureService.registeLecture(lectureReqDto);
		
		return ResponseEntity.ok().body(ResponseDto.ofDefault());
	}
	
	@GetMapping("/lectures")
	public ResponseEntity<? extends ResponseDto> getLectures(){
		
		return ResponseEntity.ok().body(DataResponseDto.of(lectureService.getLectureAll()));
	
	}
	
	@GetMapping("/lectures/{id}")
	public ResponseEntity<? extends ResponseDto> getLecturesById(@PathVariable int id){
		
		return ResponseEntity.ok().body(DataResponseDto.of(lectureService.findLectureByID(id)));
	
	}
}
