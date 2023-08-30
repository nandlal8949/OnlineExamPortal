package com.csmtech.service;

import java.util.List;

import com.csmtech.entity.Exam;

public interface ExamService {

	List<Exam> getExamList();

	Exam saveExamInfo(Exam exam);

	Exam getExamInfo(Integer examId);

	void deleteExamInfo(Integer examId);

}
