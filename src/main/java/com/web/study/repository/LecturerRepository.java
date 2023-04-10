package com.web.study.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.study.domain.entity.Lecturer;

@Mapper
public interface LecturerRepository {
	public int saveLecturer(Lecturer lecturer);
	public List<Lecturer> getLecturerAll();  //알아서 여러개의 리스트로 가지고 옴  entity가 든 list
	public Lecturer findLecturerById(int id);  //하나만 리턴이라 리스트 사용안함
}
