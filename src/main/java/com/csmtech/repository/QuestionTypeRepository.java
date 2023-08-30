package com.csmtech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csmtech.entity.QuestionType;

@Repository
public interface QuestionTypeRepository extends JpaRepository<QuestionType, Integer> {

}
