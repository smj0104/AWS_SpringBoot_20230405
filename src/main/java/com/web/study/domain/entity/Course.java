package com.web.study.domain.entity;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Builder
@Getter
@ToString
public class Course {  //property는 변수명 column은 데이터베이스명
	private int id;
	private int lecture_id;
	private int student_id;
	private LocalDate registe_date;
	private Lecture lecture;
	private Student student;
}
