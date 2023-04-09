package com.web.study.domain.entity;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Setter;
import lombok.ToString;
@Builder
@Setter
@ToString
public class LectureRegiste {
	private int id;
	private int lecture_id;
	private int student_id;
	private LocalDate registe_date;
}
