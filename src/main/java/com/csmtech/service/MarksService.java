package com.csmtech.service;

import java.util.List;

import com.csmtech.entity.Candidate;
import com.csmtech.entity.Marks;

public interface MarksService {

	List<Marks> getAllQuestionList();

	void saveAllQuestionMarksTable(Integer examSetId, Integer candId);

	Marks updateMarks(Integer candidateId, Integer questionId);

	void saveMarkswithAnswer(Marks marks);

	List<List<Object>> getAllSubjectivePendingForCheck(Integer candId, Integer examSetId);

	Marks getMarksInfoByCandIdAndQuesId(Integer candId, Integer quesId);

	List<Candidate> getAllSubjectivePendingCheck();

	Integer examCompleteAlready(Integer integer);

	void answerNotGivenQuestion(Integer candId);

}
