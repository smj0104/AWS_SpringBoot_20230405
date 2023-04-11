package com.web.study.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.web.study.dto.request.lecture.LectureReqDto;
import com.web.study.dto.response.LectureRespDto;
import com.web.study.dto.response.StudentRespDto;
import com.web.study.repository.LectureRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService {

	private final LectureRepository lectureRepository;
	@Override
	public void registeLecture(LectureReqDto lectureReqDto) {
		lectureRepository.saveLecture(lectureReqDto.toEntity());	
	}
	
	@Override
	public List<LectureRespDto> getLectureAll() {
		List<LectureRespDto> dtos = new ArrayList<>();
		lectureRepository.getLectureAll().forEach(entity -> {
		dtos.add(entity.toDto());
		});
		return dtos;
	}
	
	@Override
	public LectureRespDto findLectureByID(int id) {
		return lectureRepository.findLectureById(id).toDto();
	}
	
	@Override
	public List<LectureRespDto> searchLecture(int type, String searchValue) {  //repository는 타입이 하나뿐이라 두개를 묶을 클래스를 생성하거나 map사용
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("type", type);
		parameterMap.put("searchValue", searchValue);
		
		List<LectureRespDto> dtos = new ArrayList<>();
		lectureRepository.searchLecture(parameterMap).forEach(entity -> {  //lecture에 대한 검색결과의 list foreach로 				dto로 변환해서 응답함
			dtos.add(entity.toDto());
		});
		
		return dtos;
	}

}
