package com.csmtech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csmtech.entity.Exam;
import com.csmtech.repository.ExamRepository;

@Service
public class ExamServiceImpl implements ExamService {

	@Autowired
	private ExamRepository examRepository;
	@Override
	public List<Exam> getExamList() {
		// TODO Auto-generated method stub
		return examRepository.findAll();
	}
	@Override
	public Exam saveExamInfo(Exam exam) {
		return examRepository.save(exam);
	}
	@Override
	public Exam getExamInfo(Integer examId) {
		return examRepository.findById(examId).get();
	}
	@Override
	public void deleteExamInfo(Integer examId) {
		examRepository.deleteById(examId);
	}
	

}
