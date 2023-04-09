package com.web.study.dto.request.lecture;

import java.time.LocalDate;

import com.web.study.domain.entity.LectureRegiste;

import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
public class LectureRegisteReqDto {
	private int lectureId;
	private int studentId;
	private LocalDate registeDate;
	
	public LectureRegiste toEntity() {
		return LectureRegiste.builder()
				.lecture_id(lectureId)
				.student_id(studentId)
				.registe_date(registeDate)
				.build();
	}
}
