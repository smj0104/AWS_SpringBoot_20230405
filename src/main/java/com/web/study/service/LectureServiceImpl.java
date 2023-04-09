package com.web.study.service;

import org.springframework.stereotype.Service;

import com.web.study.domain.entity.Lecture;
import com.web.study.domain.entity.LectureRegiste;
import com.web.study.domain.entity.Lecturer;
import com.web.study.domain.entity.Student;
import com.web.study.dto.request.lecture.LectureRegisteReqDto;
import com.web.study.dto.request.lecture.LectureReqDto;
import com.web.study.dto.request.lecture.LecturerReqDto;
import com.web.study.dto.request.lecture.StudentReqDto;
import com.web.study.repository.LectureRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService {

	private final LectureRepository lectureRepository;
	
	@Override
	public void registeLecture(LectureReqDto lectureReqDto) {
		//DTO -> ENTITY 변환  				DTO = 클라와 컨트롤러 사이에서 사용  ENTITY = reposiitory와 데이터베이스 사이에서 사용
		Lecture lecture = lectureReqDto.toEntity();
		System.out.println("변환: " + lecture);
		lectureRepository.registe(lecture);
	}

	@Override
	public void registeLecture2(LectureRegisteReqDto lectureRegisteReqDto) {
		LectureRegiste lectureRegiste = lectureRegisteReqDto.toEntity();
		System.out.println("변환완료:" + lectureRegiste);
		lectureRepository.registe2(lectureRegiste);
		
	}

	@Override
	public void registeLecture3(LecturerReqDto lecturerReqDto) {
		Lecturer lecturer = lecturerReqDto.toEntity();
		System.out.println("변환완료:" + lecturer);
		lectureRepository.registe3(lecturer);
		
	}

	@Override
	public void registeLecture4(StudentReqDto studentReqDto) {
		Student student = studentReqDto.toEntity();
		System.out.println("변환완료:" + student);
		lectureRepository.registe4(student);
		
	}

}
