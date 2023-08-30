package com.csmtech.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.csmtech.entity.Marks;

@Repository
public interface MarksRepository extends JpaRepository<Marks, Integer> {

	@Modifying
	@Transactional
	void deleteByQuestionId(Integer questionId);

	List<Marks> findByCandidateId(Integer candId);

	@Modifying
	@Transactional
	void deleteByCandidateId(Integer candId);

	Marks findByQuestionIdAndCandidateId(Integer candId, Integer quesId);

}
