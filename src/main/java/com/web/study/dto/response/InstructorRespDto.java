package com.web.study.dto.response;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter //getter가 있어야 jackson이 가져올수있다 요청은 setter (객체에 값을 주입하기위해)
public class InstructorRespDto {
	private int id;
	private String name;
	private LocalDate birthDate;
}
