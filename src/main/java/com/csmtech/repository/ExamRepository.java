package com.csmtech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csmtech.entity.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer> {

}
