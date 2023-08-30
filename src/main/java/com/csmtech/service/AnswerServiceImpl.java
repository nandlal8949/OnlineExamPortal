package com.csmtech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csmtech.entity.Answer;
import com.csmtech.repository.AnswerRepository;

@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	private AnswerRepository answerRepository;
	@Override
	public void saveAnswers(List<Answer> ansList) {
		answerRepository.saveAll(ansList);
	}
	
	@Override
	public void deleteQuestion(Integer questionId) {
		answerRepository.deleteByQuestionId(questionId);
		
	}

	@Override
	public void deleteQuestionById(Integer answerId) {
		answerRepository.deleteById(answerId);		
	}
	

}
