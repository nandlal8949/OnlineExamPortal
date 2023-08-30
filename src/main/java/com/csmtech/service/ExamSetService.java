package com.csmtech.service;

import java.util.List;

import com.csmtech.entity.ExamSet;

public interface ExamSetService {

	ExamSet postExamSet(ExamSet examSet);

	List<ExamSet> getExamSets();

	ExamSet updateExamSet(Integer examSetId);

	void deleteExamSet(Integer examSetId);

	void deleteExamSetByitemId(Integer itemId);

	Integer getUncompleteExamSets();

}
