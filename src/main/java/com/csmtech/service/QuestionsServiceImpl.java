package com.csmtech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csmtech.entity.Question;
import com.csmtech.repository.QuestionsRepository;

@Service
public class QuestionsServiceImpl implements QuestionsService {

	@Autowired
	private QuestionsRepository questionsRepository;
	@Override
	public List<Question> getAllQuestions() {
		return questionsRepository.findAll();
	}
	@Override
	public Question saveQuestion(Question question) {
		return questionsRepository.save(question);
		
	}
	@Override
	public void deleteQuestion(Integer questionId) {
		questionsRepository.deleteById(questionId);
		
	}
	@Override
	public Question updateQuestion(Integer questionId) {
		return questionsRepository.findById(questionId).get();
	}
	@Override
	public Question getQuestionById(Integer questionId) {
		return questionsRepository.findById(questionId).get();
	}
	@Override
	public Integer getUncompleteQuestionSet() {
		Integer count = (int) questionsRepository.findAll().stream().filter(t->t.getQuestionStatus().equals("Pending")).count();
		return count;
	}

}
