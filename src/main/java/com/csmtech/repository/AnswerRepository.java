package com.csmtech.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.csmtech.entity.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer>{

	@Transactional
	@Modifying
	void deleteByQuestionId(Integer questionId);

}
