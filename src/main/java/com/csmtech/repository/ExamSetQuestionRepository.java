package com.csmtech.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.csmtech.entity.ExamSetQuestion;

@Repository
public interface ExamSetQuestionRepository extends JpaRepository<ExamSetQuestion, Integer> {

	List<ExamSetQuestion> findByQuestionId(Integer examSetId);

	@Transactional
	@Modifying
	void deleteByExamCodeId(Integer examSetId);

	ExamSetQuestion findByQuestionIdAndExamCodeId(int parseInt, int parseInt2);

	List<ExamSetQuestion> findByExamCodeId(Integer examSetId);

	@Transactional
	@Modifying
	void deleteByQuestionId(Integer questionId);

}
