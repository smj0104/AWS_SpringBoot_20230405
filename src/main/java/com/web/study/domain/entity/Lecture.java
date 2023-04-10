package com.web.study.domain.entity;

import com.web.study.dto.response.LectureRespDto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Lecture {
	private int id;
	private String lecture_name;
	private int lecture_price;
	private int lecturer_id;
	
	public LectureRespDto toDto() {
		return LectureRespDto.builder()
				.id(id)
				.lectureName(lecture_name)
				.lecturePrice(lecture_price)
				.lecturerId(lecturer_id)
				.build();
	}
}
