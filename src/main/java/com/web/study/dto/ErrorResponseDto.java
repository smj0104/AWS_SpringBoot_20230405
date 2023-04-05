package com.web.study.dto;

import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ErrorResponseDto extends ResponseDto {
	
	private Map<String, String> errorData;
	
	private ErrorResponseDto(HttpStatus status) {
		super(true, status.value(), "Failed");
	}
	
	private ErrorResponseDto(HttpStatus status, Exception e) {
		super(true,  status.value(), e.getMessage());
	}
	private ErrorResponseDto(HttpStatus status, Exception e, Map<String, String> errorData) {
		super(true,  status.value(), e.getMessage());
		this.errorData = errorData;
	}
	
	public static ErrorResponseDto of(HttpStatus status) {
		return new ErrorResponseDto(status);
	}
	
	public static ErrorResponseDto of(HttpStatus status, Exception e) {
		return new ErrorResponseDto(status, e);
	}
	
	public static ErrorResponseDto of(HttpStatus status, Exception e, Map<String, String> errorData) {
		return new ErrorResponseDto(status, e, errorData);
	}
	public static ErrorResponseDto empty() {
		return new ErrorResponseDto(null);
	}
	

	

}
