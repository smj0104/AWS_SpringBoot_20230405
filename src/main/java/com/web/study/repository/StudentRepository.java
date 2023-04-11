package com.web.study.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.study.domain.entity.Student;

@Mapper  //인터페이스 필수
public interface StudentRepository {
	
	public int saveStudent(Student student);
	public List<Student> getStudentAll();
	public Student findStudentById(int id);	
}
