package com.web.study.service;

import java.util.ArrayList;
import java.util.List;

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

}
