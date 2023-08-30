package com.csmtech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csmtech.entity.ExamSet;
import com.csmtech.entity.ExamSetQuestion;
import com.csmtech.repository.ExamSetRepository;
@Service
public class ExamSetServiceImpl implements ExamSetService {
	@Autowired
	private ExamSetRepository examSetRepository;
	
	@Override
	public ExamSet postExamSet(ExamSet examSet) {
		
		return examSetRepository.save(examSet);
	}

	@Override
	public List<ExamSet> getExamSets() {
		return examSetRepository.findAll();
	}

	@Override
	public ExamSet updateExamSet(Integer examSetId) {
		return examSetRepository.findById(examSetId).get();
	}

	@Override
	public void deleteExamSet(Integer examSetId) {
		examSetRepository.deleteById(examSetId);
	}

	@Override
	public void deleteExamSetByitemId(Integer itemId) {
		System.out.println(examSetRepository.deleteExamSetByItemId(itemId));
		examSetRepository.deleteExamSetByItemId(itemId);
		
	}

	@Override
	public Integer getUncompleteExamSets() {
		Integer count = 0;
		for (ExamSet examSet : examSetRepository.findAll()) {
			Integer totalQues = examSet.getObjQues()+examSet.getSubQues();
			List<ExamSetQuestion> question = examSet.getExamSetQuestion();
			if(totalQues != question.size()) {
				count++;				
			}
		}
		return count;
	}

}
