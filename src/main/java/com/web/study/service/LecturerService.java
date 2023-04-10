package com.web.study.service;

import java.util.List;

import com.web.study.dto.request.lecturer.LecturerReqDto;
import com.web.study.dto.response.LecturerRespDto;

public interface LecturerService {

	public void registeLecturer(LecturerReqDto lectureReqDto);
	public List<LecturerRespDto> getLecturerAll();
	public LecturerRespDto findLecturerById(int id);  //엔티티를 리턴해서 스네이크 표기법으로 나옴
}
