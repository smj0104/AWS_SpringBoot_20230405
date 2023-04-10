package com.web.study.domain.entity;

import java.time.LocalDate;

import com.web.study.dto.response.StudentRespDto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder  //부분적으로 필요한 부분만 생성
@Getter
@ToString
public class Student {
	private int id;
	private String name;
	private LocalDate birth_date;
	
	public StudentRespDto toDto() {
		return StudentRespDto.builder()
				.id(id)
				.name(name)
				.birthDate(birth_date)
				.build();
	}
}
