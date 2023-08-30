package com.csmtech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csmtech.entity.QuestionType;
import com.csmtech.repository.QuestionTypeRepository;

@Service
public class QuestionTypeServiceImpl implements QuestionsTypeService {

	@Autowired
	private QuestionTypeRepository questionTypeRepository;
	@Override
	public List<QuestionType> getAllQuestionType() {
		return questionTypeRepository.findAll();
	}

}
