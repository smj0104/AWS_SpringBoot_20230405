package com.web.study.service;

import com.web.study.dto.request.lecture.LectureRegisteReqDto;
import com.web.study.dto.request.lecture.LectureReqDto;
import com.web.study.dto.request.lecture.LecturerReqDto;
import com.web.study.dto.request.lecture.StudentReqDto;

public interface LectureService {
	
	public void registeLecture(LectureReqDto lectureReqDto);
	public void registeLecture2(LectureRegisteReqDto lectureRegisteReqDto);
	public void registeLecture3(LecturerReqDto lecturerReqDto);
	public void registeLecture4(StudentReqDto studentReqDto);
}
