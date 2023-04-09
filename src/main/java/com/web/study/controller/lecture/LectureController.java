package com.web.study.controller.lecture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.domain.entity.Lecturer;
import com.web.study.dto.ResponseDto;
import com.web.study.dto.request.lecture.LectureRegisteReqDto;
import com.web.study.dto.request.lecture.LectureReqDto;
import com.web.study.dto.request.lecture.LecturerReqDto;
import com.web.study.dto.request.lecture.StudentReqDto;
import com.web.study.service.LectureService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

public class LectureController {
	
	/**
	 * 학생정보 id, name, birth_date
	 * 수강정보 id, lecture_id(강의), student_id(학생), register_date(등록일)
	 * 강의정보	id, lecture_name(강의명), price(가격), lecturer_id(강사)
	 * 강사정보 id, name, birth_date
	 */

	private final LectureService lectureService;
	
	// Create
	@PostMapping("/lecture")
	public ResponseEntity<? extends ResponseDto> register(LectureReqDto lectureReqDto) {
		
		lectureService.registeLecture(lectureReqDto);
		
		return ResponseEntity.ok().body(ResponseDto.ofDefault());
	}
	
	@PostMapping("/lectureregiste")
	public ResponseEntity<? extends ResponseDto> register2(LectureRegisteReqDto lectureRegisteReqDto) {
		
		lectureService.registeLecture2(lectureRegisteReqDto);
		
		return ResponseEntity.ok().body(ResponseDto.ofDefault());
	}
	
	@PostMapping("/lecturer")
	public ResponseEntity<? extends ResponseDto> register3(LecturerReqDto lecturerReqDto) {
		
		lectureService.registeLecture3(lecturerReqDto);
		
		return ResponseEntity.ok().body(ResponseDto.ofDefault());
	}
	
	@PostMapping("/student")
	public ResponseEntity<? extends ResponseDto> register4(StudentReqDto  studentReqDto) {
		
		lectureService.registeLecture4(studentReqDto);
		
		return ResponseEntity.ok().body(ResponseDto.ofDefault());
	}
	// Read
	public ResponseEntity<? extends ResponseDto> get() {
		
		
		return ResponseEntity.ok().body(ResponseDto.ofDefault());
	}
	// Update
	public ResponseEntity<? extends ResponseDto> modify() {
		return ResponseEntity.ok().body(ResponseDto.ofDefault());
	}
	// Delete
	public ResponseEntity<? extends ResponseDto> remove() {
		return ResponseEntity.ok().body(ResponseDto.ofDefault());
	}
}
