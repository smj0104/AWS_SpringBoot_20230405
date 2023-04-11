package com.web.study.domain.entity;

import java.time.LocalDate;

import com.web.study.dto.response.CourseRespDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor //들어오면 allargs는 사라짐` xml의 resultmap사용시 필요함
@AllArgsConstructor
public class Course {  //property는 변수명 column은 데이터베이스명
	private int csm_id;
	private int ltm_id;
	private int sdm_id;
	private LocalDate registe_date;
	private Lecture lecture;  //ltm_id sdm_id를 조인 할 수 있어서 객체를 통으로 넣어둠
	private Student student;
	
	public CourseRespDto toDto() {
		String lectureName = null;
		int lecturePrice = 0;
		String instructorName = null;
		String studentName = null;
		
		if(lecture != null) {
			lectureName = lecture.getLtm_name();
			lecturePrice = lecture.getLtm_price();
			if(lecture.getInstructor() != null) {
				instructorName = lecture.getInstructor().getItm_name();
			}
		}
		
		if(student != null) {
			studentName = student.getSdm_name();
		}
		
		return CourseRespDto.builder()
				.courseid(csm_id)
				.registeDate(registe_date)
				.lectureName(lectureName)
				.lecturePrice(lecturePrice)
				.instructorName(instructorName)
				.studentName(studentName)
				.build();
	}
}
