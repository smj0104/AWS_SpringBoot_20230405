package com.web.study.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.web.study.domain.entity.Lecturer;
import com.web.study.dto.request.lecturer.LecturerReqDto;
import com.web.study.dto.response.LecturerRespDto;
import com.web.study.repository.LecturerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LecturerServiceImpl implements LecturerService {

	private final LecturerRepository lecturerepoRepository;
	@Override
	public void registeLecturer(LecturerReqDto lecturerReqDto) {
		lecturerepoRepository.saveLecturer(lecturerReqDto.toEntity());
		
	}
	@Override
	public List<LecturerRespDto> getLecturerAll() {
		List<LecturerRespDto> dtos = new ArrayList<>();
		lecturerepoRepository.getLecturerAll().forEach(entity -> {
			dtos.add(entity.toDto());
		});
		
		return dtos;
	}
	@Override
	public LecturerRespDto findLecturerById(int id) {
		return lecturerepoRepository.findLecturerById(id).toDto();//findlecturerebyid가 entity객체를 들고옴 toDto로 변환
	}

}
