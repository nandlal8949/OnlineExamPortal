package com.csmtech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csmtech.entity.ExamSetQuestion;
import com.csmtech.repository.ExamSetQuestionRepository;

@Service
public class ExamSetQuestionServiceImpl implements ExamSetQuestionService {

	@Autowired
	private ExamSetQuestionRepository examSetQuestionRepository;
	@Override
	public void saveQuestionInSet(List<ExamSetQuestion> collect) {
		examSetQuestionRepository.saveAll(collect);

	}
	@Override
	public List<ExamSetQuestion> getAllQuestionList(Integer examSetId) {
		return examSetQuestionRepository.findByQuestionId(examSetId);
	}
	@Override
	public ExamSetQuestion getExamSetInfo(int parseInt, int parseInt2) {
		return examSetQuestionRepository.findByQuestionIdAndExamCodeId(parseInt,parseInt2);
	}
	@Override
	public void saveExamSetQuestion(ExamSetQuestion examSetQuestion) {
		examSetQuestionRepository.save(examSetQuestion);
		
	}
	@Override
	public void deleteExamSetQuestion(Integer examSetId) {
		examSetQuestionRepository.deleteByExamCodeId(examSetId);
		
	}
	@Override
	public List<ExamSetQuestion> getAllQuestionByExamId(Integer examSetId) {
		return examSetQuestionRepository.findByExamCodeId(examSetId);
	}
	@Override
	public void deleteExamSetQuestionByQuestionId(Integer questionId) {
		examSetQuestionRepository.deleteByQuestionId(questionId);
		
	}

}
