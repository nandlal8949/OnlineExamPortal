package com.csmtech.service;

import java.util.List;

import com.csmtech.entity.Answer;

public interface AnswerService {

	void saveAnswers(List<Answer> ansList);

	void deleteQuestion(Integer questionId);

	void deleteQuestionById(Integer answerId);

}
