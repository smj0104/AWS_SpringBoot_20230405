package com.web.study.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.study.domain.entity.Instructor;

@Mapper
public interface InstructorRepository {
	public int saveInstructor(Instructor instructor);
	public List<Instructor> getInstructorAll();  //알아서 여러개의 리스트로 가지고 옴  entity가 든 list
	public Instructor findInstructorById(int id);  //하나만 리턴이라 리스트 사용안함
}
