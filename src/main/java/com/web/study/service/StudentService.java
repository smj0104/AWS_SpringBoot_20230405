package com.web.study.service;

import java.util.List;

import com.web.study.domain.entity.Student;
import com.web.study.dto.request.student.StudentReqDto;
import com.web.study.dto.response.StudentRespDto;

public interface StudentService {

	public void registeStudent(StudentReqDto studentReqDto);
	public List<StudentRespDto> getStudentAll();
	public StudentRespDto findStudentById(int id);
}
