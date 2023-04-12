package com.web.study.domain.entity;

import com.web.study.dto.response.LectureRespDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Lecture {
	private int ltm_id;
	private String ltm_name;
	private int ltm_price;
	private int itm_id;
	private Instructor instructor;
	
	
	public LectureRespDto toDto() {

		String instructorName = null;
		
		if(instructor != null) {  //instructor가 null이면 get할수없기에 먼저 검사
			instructorName = instructor.getItm_name();
		}

		return LectureRespDto.builder()
				.lectureId(ltm_id)
				.lectureName(ltm_name)
				.lecturePrice(ltm_price)
				.instructorName(instructorName)
				.build();
	}
}
