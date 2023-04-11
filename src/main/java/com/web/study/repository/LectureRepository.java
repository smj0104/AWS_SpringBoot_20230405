package com.web.study.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.web.study.domain.entity.Lecture;
import com.web.study.domain.entity.Student;

@Mapper
public interface LectureRepository {
	
	public int saveLecture(Lecture lecture);
	public List<Lecture> getLectureAll();
	public Lecture findLectureById(int id);
	public List<Lecture> searchLecture(Map<String, Object> parameterMap);//검색된것이 없어도 list로 
	
}
