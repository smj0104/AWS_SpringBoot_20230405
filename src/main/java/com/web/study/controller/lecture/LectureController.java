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
	
	//searchlecture (수강목록이 아닌 강의만 검색하기) entity에서 toDto만들기  검색옵션= /1강의명조회 2강사조회
	
	@GetMapping("/search/lectures")
	public ResponseEntity<? extends ResponseDto> searchLecture(int type, String searchValue) {  //아예 안넣으면 null 값을 안넣으면 공백
		return ResponseEntity.ok().body(DataResponseDto.of(lectureService.searchLecture(type, searchValue)));
	}
}
