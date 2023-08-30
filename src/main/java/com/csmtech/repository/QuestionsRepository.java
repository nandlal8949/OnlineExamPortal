package com.csmtech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csmtech.entity.Question;

@Repository
public interface QuestionsRepository extends JpaRepository<Question, Integer> {

}
