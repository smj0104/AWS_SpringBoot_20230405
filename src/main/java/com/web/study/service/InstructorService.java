package com.web.study.service;

import java.util.List;

import com.web.study.dto.request.instructor.InstructorReqDto;
import com.web.study.dto.response.InstructorRespDto;

public interface InstructorService {

	public void registeInstructor(InstructorReqDto lectureReqDto);
	public List<InstructorRespDto> getInstructorAll();
	public InstructorRespDto findInstructorById(int id);  //엔티티를 리턴해서 스네이크 표기법으로 나옴
}
