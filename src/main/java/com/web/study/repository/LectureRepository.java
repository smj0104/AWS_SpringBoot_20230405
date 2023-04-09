package com.web.study.repository;

import org.apache.ibatis.annotations.Mapper;

import com.web.study.domain.entity.Lecture;
import com.web.study.domain.entity.LectureRegiste;
import com.web.study.domain.entity.Lecturer;
import com.web.study.domain.entity.Student;

@Mapper
public interface LectureRepository {
	
	public int registe(Lecture lecture);
	public int registe2(LectureRegiste lectureRegiste);	
	public int registe3(Lecturer lecturer);	
	public int registe4(Student  student);	
}
