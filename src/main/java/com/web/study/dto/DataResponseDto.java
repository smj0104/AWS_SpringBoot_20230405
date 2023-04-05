package com.web.study.dto;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class DataResponseDto<T> extends ResponseDto {

	private final T data;
	
	private DataResponseDto(T data) {//HttpStatus = enum (키워드로 생성가능)
		super(true, HttpStatus.OK.value(), "Successfully");  //super == responseDto  okvalue 200 badrequest 400 create 201
		this.data = data;
	}
	
	private DataResponseDto(T data, String message) {
		super(true, HttpStatus.OK.value(), message);
		this.data = data;
	}
	
	public static <T> DataResponseDto<T> of(T data) {  
		return new DataResponseDto<T>(data);
	}
	
	public static <T> DataResponseDto<T> of(T data, String message) {
		return new DataResponseDto<T>(data, message);
	}
	
	public static <T> DataResponseDto<T> empty() {
		return new DataResponseDto<T>(null);
	}
	

}
