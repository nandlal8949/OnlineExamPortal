
package com.csmtech.service;

import java.util.List;

import com.csmtech.entity.ExamSetQuestion;

public interface ExamSetQuestionService {

	void saveQuestionInSet(List<ExamSetQuestion> collect);

	List<ExamSetQuestion> getAllQuestionList(Integer examSetId);

	ExamSetQuestion getExamSetInfo(int parseInt, int parseInt2);

	void saveExamSetQuestion(ExamSetQuestion examSetQuestion);

	void deleteExamSetQuestion(Integer examSetId);

	List<ExamSetQuestion> getAllQuestionByExamId(Integer examSetId);

	void deleteExamSetQuestionByQuestionId(Integer questionId);

}
