package com.csmtech.service;

import java.util.List;

import com.csmtech.entity.Question;

public interface QuestionsService {

	List<Question> getAllQuestions();

	Question saveQuestion(Question question);

	void deleteQuestion(Integer questionId);

	Question updateQuestion(Integer questionId);

	Question getQuestionById(Integer questionId);

	Integer getUncompleteQuestionSet();

}
